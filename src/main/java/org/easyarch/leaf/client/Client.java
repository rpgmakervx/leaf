package org.easyarch.leaf.client;

import org.easyarch.leaf.client.handler.InitializeHandler;
import org.easyarch.leaf.demo.Service;
import org.easyarch.leaf.protocol.Request;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by xingtianyu(code4j) on 2017-8-20.
 */
public class Client {
    private ChannelFuture future;
    private Bootstrap b;

    public void connect(String ip,int port){
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                    .handler(new InitializeHandler());
            future = b.connect(ip, port).sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        }
    }

    public void send(Request entity){
        future.channel().writeAndFlush(entity);
        System.out.println("send a message:"+entity);
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connect("127.0.0.1",8081);
        Request entity = new Request();
        entity.setLookUp(Service.class.getName());
        entity.setMethodName("calculate");
        entity.setParamTypes(new Class[]{int.class,int.class});
        entity.setParamVals(new Object[]{1,2});
        client.send(entity);
    }
}
