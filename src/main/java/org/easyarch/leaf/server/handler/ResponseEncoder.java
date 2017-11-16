package org.easyarch.leaf.server.handler;

import org.easyarch.leaf.protocol.Response;
import org.easyarch.leaf.serializer.ProtobufSerializer;
import org.easyarch.leaf.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 *
 * @author xingtianyu(code4j)
 * @date 2017-8-20
 */
public class ResponseEncoder extends MessageToByteEncoder<Response> {
    private Serializer<Response> serializer;

    public ResponseEncoder(){
        serializer = new ProtobufSerializer<>();
    }
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Response entity, ByteBuf byteBuf) throws Exception {
        byte[] data = serializer.serialize(entity);
        byteBuf.writeBytes(data);
    }
}
