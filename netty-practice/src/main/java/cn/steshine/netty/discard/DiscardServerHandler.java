package cn.steshine.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        ByteBuf in = (ByteBuf) msg;
        System.out.print(in.toString(io.netty.util.CharsetUtil.US_ASCII));
        ctx.write(msg);
        // notice  : it not invoke release() method. because netty release it when it is written out to the wire
        // notice : ctx.write() dose not written out to wire .it buffed internally
        ctx.flush();//make message which buffed internally out to wire
        //in.release();
        /*try {
            while (in.isReadable()) { // (1)
                System.out.print((char) in.readByte());
                //System.out.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
