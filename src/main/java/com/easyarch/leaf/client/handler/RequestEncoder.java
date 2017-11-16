package com.easyarch.leaf.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.easyarch.vsf.protocol.Request;
import org.easyarch.vsf.serializer.ProtobufSerializer;
import org.easyarch.vsf.serializer.Serializer;

/**
 * Created by xingtianyu(code4j) on 2017-8-20.
 */
public class RequestEncoder extends MessageToByteEncoder<Request> {
    private Serializer<Request> serializer;

    public RequestEncoder(){
        serializer = new ProtobufSerializer<>();
    }
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Request entity, ByteBuf byteBuf) throws Exception {
        byte[] data = serializer.serialize(entity);
        byteBuf.writeBytes(data);
//        System.out.println("编码数据："+entity);
    }
}
