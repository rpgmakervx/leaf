package org.easyarch.leaf.server.handler;

import org.easyarch.leaf.kits.ByteKits;
import org.easyarch.leaf.protocol.Request;
import org.easyarch.leaf.serializer.ProtobufSerializer;
import org.easyarch.leaf.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by xingtianyu(code4j) on 2017-8-20.
 */
public class RequestDecoder extends ByteToMessageDecoder {

    private Serializer<Request> serializer;

    public RequestDecoder(){
        serializer = new ProtobufSerializer<>();
    }
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() <= 0){
            return;
        }
        byte[] data = ByteKits.readByteBuf(byteBuf);
        Request message = serializer.deserialize(data,Request.class);
//        System.out.println("解码器得到的消息："+message);
        list.add(message);
    }
}
