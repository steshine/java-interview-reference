package cn.steshine.netty.chunked;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.pipeline.PipelineConfigurators;
import io.reactivex.netty.protocol.http.client.HttpClientRequest;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by skychen on 2018/11/29.
 */
public class Rxnetty {
    public static void main(String[] args) {
        try {
            new Rxnetty().test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test() throws Exception {
        RxNetty.createHttpClient("10.16.11.40", 8061, PipelineConfigurators
                .<ByteBuf>sseClientConfigurator())
                .submit(HttpClientRequest.createGet("/hystrix.stream"))
                .flatMap(response -> {
                    return response.getContent()
                            .doOnSubscribe(() -> System.out.println("Turbine => Aggregate Stream from URI: "))
                            .doOnUnsubscribe(() -> System.out.println("Turbine => Unsubscribing Stream: "))
                            //.takeUntil(streamRemoves.filter(a -> a.getUri().equals(streamAction.getUri()))) // unsubscribe when we receive a remove event
                            .map(s -> s.getEventData());
                }).toBlocking().forEach(result -> System.out.println(result));
    }

    private static URI stripUserInfoFromUriIfDefined(URI uri) {
        final URI uriToUse;
        if (uri.getUserInfo() != null) {
            try {
                uriToUse = new URI(uri.getScheme(), null, uri.getHost(), uri.getPort(), uri.getPath(), uri.getQuery(), uri.getFragment());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        } else {
            uriToUse = uri;
        }
        return uriToUse;
    }
}
