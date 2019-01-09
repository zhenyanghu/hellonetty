package com.rocky.hellonetty.client.time;

import java.util.List;

import com.rocky.hellonetty.server.time.Time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * Created by Rocky on 2019-01-02.
 * 自定义客户端数据解码类
 */
public class TimeDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		/**
		 * 有新数据接收时调用
		 * 为了防止分包现象，先将数据存入内部缓存，到达满足条件之后再进行解码
		 */
		if (in.readableBytes() < 4) {
			return;
		}
		
		// out添加对象则表示解码成功
		out.add(new Time(in.readUnsignedInt()));
	}

	
}
