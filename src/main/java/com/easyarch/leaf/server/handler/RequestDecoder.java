package com.easyarch.leaf.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.easyarch.vsf.kits.ByteKits;
import org.easyarch.vsf.protocol.Request;
import org.easyarch.vsf.serializer.ProtobufSerializer;
import org.easyarch.vsf.serializer.Serializer;

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
