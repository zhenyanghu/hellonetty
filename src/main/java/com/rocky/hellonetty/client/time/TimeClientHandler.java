package com.rocky.hellonetty.client.time;

import com.rocky.hellonetty.server.time.Time;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by Rocky on 2019-01-02.
 * 自定义客户端业务逻辑类
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// 直接将信息转换成Time类型输出即可
		Time time = (Time) msg;
		System.out.println(time);
		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
	
}
