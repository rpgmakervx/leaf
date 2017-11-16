package com.easyarch.leaf.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.easyarch.vsf.protocol.ErrorCode;
import org.easyarch.vsf.protocol.Request;
import org.easyarch.vsf.protocol.Response;

import java.lang.reflect.Method;

/**
 * Created by xingtianyu(code4j) on 2017-8-20.
 */
public class ProcessorHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("server active");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Request entity = (Request) msg;
        Response response = new Response();
        try {
            Class service = Class.forName(entity.getLookUp());
            Object serviceInstance = service.newInstance();
            Method method = service.getMethod(entity.getMethodName(), entity.getParamTypes());
            Object result = method.invoke(serviceInstance,entity.getParamVals());
            response.setResult(result);
            response.setCode(0);
            response.setMessage("success");
            ctx.writeAndFlush(response);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Response response = new Response();
        response.setCode(ErrorCode.SERVER_ERROR);
        response.setMessage(cause.getMessage());
        ctx.writeAndFlush(response);
    }
}
