package cn.steshine.netty.chunked;

import com.netflix.hystrix.HystrixCollapserMetrics;
import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.HystrixThreadPoolMetrics;
import com.netflix.hystrix.serial.SerialHystrixDashboardData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;
import rx.subscriptions.MultipleAssignmentSubscription;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * Created by skychen on 2018/11/22.
 */
public class ChunkedStreamHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    public static final String DEFAULT_HYSTRIX_PREFIX = "/hystrix.stream";
    public static final int DEFAULT_INTERVAL = 2000;

    private static final byte[] HEADER = "data: ".getBytes(Charset.defaultCharset());
    private static final byte[] FOOTER = {10, 10};
    private static final int EXTRA_SPACE = HEADER.length + FOOTER.length;
    private final long interval;
    private final String hystrixPrefix;

    public ChunkedStreamHandler(String hystrixPrefix, long interval) {
        this.interval = interval;
        this.hystrixPrefix = hystrixPrefix;
    }

    public ChunkedStreamHandler(){
        this(DEFAULT_HYSTRIX_PREFIX,DEFAULT_INTERVAL);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) throws Exception {
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        if(fullHttpRequest.uri().startsWith(hystrixPrefix)){
            handleHystrixRequest(ctx,response);
            return;
        }
        ctx.fireChannelRead(fullHttpRequest);
    }

    private void handleHystrixRequest(ChannelHandlerContext ctx, final HttpResponse response) {
        writeHeaders(response);
        ctx.write(response);
        final Subject<Void, Void> subject = PublishSubject.create();
        final MultipleAssignmentSubscription subscription = new MultipleAssignmentSubscription();
        final Channel channel = ctx.channel();
        Subscription actionSubscription = Observable.interval(interval, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long tick) {
                        if (!channel.isOpen()) {
                            subscription.unsubscribe();
                            return;
                        }
                        try {
                            for (HystrixCommandMetrics commandMetrics : HystrixCommandMetrics.getInstances()) {
                                writeMetric(SerialHystrixDashboardData.toJsonString(commandMetrics), channel);
                            }
                            for (HystrixThreadPoolMetrics threadPoolMetrics : HystrixThreadPoolMetrics.getInstances()) {
                                writeMetric(SerialHystrixDashboardData.toJsonString(threadPoolMetrics), channel);
                            }
                            for (HystrixCollapserMetrics collapserMetrics : HystrixCollapserMetrics.getInstances()) {
                                writeMetric(SerialHystrixDashboardData.toJsonString(collapserMetrics), channel);
                            }
                        } catch (Exception e) {
                            subject.onError(e);
                        }
                    }
                });
        subscription.set(actionSubscription);
    }


    private void writeMetric(String json, Channel response) {
        byte[] bytes = json.getBytes(Charset.defaultCharset());
        ByteBuf byteBuf = UnpooledByteBufAllocator.DEFAULT.buffer(bytes.length + EXTRA_SPACE);
        byteBuf.writeBytes(HEADER);
        byteBuf.writeBytes(bytes);
        byteBuf.writeBytes(FOOTER);
        response.writeAndFlush(byteBuf);
    }

    private void writeHeaders(HttpResponse response) {
        response.headers().add("Content-Type", "text/event-stream;charset=UTF-8");
        response.headers().add("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
        response.headers().add("Pragma", "no-cache");
        response.headers().set(HttpHeaders.Names.TRANSFER_ENCODING, HttpHeaders.Values.CHUNKED);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }
}
