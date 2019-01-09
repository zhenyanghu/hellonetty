package com.rocky.hellonetty.server.time;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by Rocky on 2019-01-02.
 * 自定义服务器的业务逻辑类
 * 
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

	/**
	 * 连接建立的时候并且准备通讯时调用
	 */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 发送当前时间信息
    	ChannelFuture f = ctx.writeAndFlush(new Time());
    	// 发送完毕之后关闭channel
        f.addListener(ChannelFutureListener.CLOSE);
    }

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
    
    
}
