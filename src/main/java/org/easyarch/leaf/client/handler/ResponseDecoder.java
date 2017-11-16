package org.easyarch.leaf.client.handler;

import org.easyarch.leaf.kits.ByteKits;
import org.easyarch.leaf.protocol.Response;
import org.easyarch.leaf.serializer.ProtobufSerializer;
import org.easyarch.leaf.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

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
        list.add(response);
    }
}
