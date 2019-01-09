package com.rocky.hellonetty.server.hello;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Rocky on 2019-01-02.
 */
public class HelloServer {

	private int port;
	
	public HelloServer(int port) {
		this.port = port;
	}
	
	public void run() {
		// 用来接收进来的连接
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// 用来处理已经接收的连接
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		System.out.println("准备运行的端口：" + port);

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
            		.channel(NioServerSocketChannel.class)            // 这里告诉Channel如何接收新的连接
            		.childHandler(new ChannelInitializer<SocketChannel>() {
                		@Override
                		protected void initChannel(SocketChannel ch) throws Exception {
                    		// 自定义处理类
                    		ch.pipeline().addLast(new HelloServerHandler());
                		}
            		})
            		.option(ChannelOption.SO_BACKLOG, 128)
            		.childOption(ChannelOption.SO_KEEPALIVE, true);
            
            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(port).sync();
            
            // 等待服务器socket关闭
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            	workerGroup.shutdownGracefully();
            	bossGroup.shutdownGracefully();
        }
    }
	
	public static void main(String[] args) {
		 int port = 10110;
	     new HelloServer(port).run();

	}
	
}
