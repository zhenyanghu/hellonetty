package com.rocky.hellonetty.server.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by Rocky on 2019-01-02.
 * 自定义服务器数据编码类
 */
public class TimeEncoder extends MessageToByteEncoder<Time> {

	// 发送数据时调用
	@Override
	protected void encode(ChannelHandlerContext ctx, Time msg, ByteBuf out) throws Exception {
		// 只传输当前时间，精确到秒
		out.writeInt((int) msg.value());
	}

}
