package cn.steshine.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by skychen on 2018/11/27.
 */
public class InLogHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InLogHandler");
        ByteBuf byteBuf = (ByteBuf) msg;
        String input = byteBuf.toString(io.netty.util.CharsetUtil.US_ASCII);
        System.out.println(input);
        if ("a".equalsIgnoreCase(input)) {
            ByteBuf buffer = ctx.alloc().buffer(4);
            buffer.writeInt(123);
            ctx.writeAndFlush(buffer);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

}

