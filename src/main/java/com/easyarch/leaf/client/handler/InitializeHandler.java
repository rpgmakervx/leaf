package com.easyarch.leaf.client.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by xingtianyu(code4j) on 2017-8-21.
 */
public class InitializeHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new RequestEncoder());
        socketChannel.pipeline().addLast(new ResponseDecoder());
        socketChannel.pipeline().addLast(new ReceiveHandler());
    }
}
