package com.easyarch.leaf.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.easyarch.vsf.kits.ByteKits;
import org.easyarch.vsf.protocol.Request;
import org.easyarch.vsf.protocol.Response;
import org.easyarch.vsf.serializer.ProtobufSerializer;
import org.easyarch.vsf.serializer.Serializer;

import java.util.List;

/**
 * Created by xingtianyu(code4j) on 2017-8-20.
 */
public class ResponseDecoder extends ByteToMessageDecoder {

    private Serializer<Response> serializer;

    public ResponseDecoder(){
        serializer = new ProtobufSerializer<>();
    }
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() <= 0){
            return;
        }
        byte[] data = ByteKits.readByteBuf(byteBuf);
        Response response = serializer.deserialize(data,Response.class);
//        System.out.println("解码器得到的消息："+response);
        list.add(response);
    }
}
