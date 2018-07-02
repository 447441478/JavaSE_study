package cn.hncu.javaSE.net.tcp.hello;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 2018年5月10日 上午10:48:54
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *  通过 ServerSocket 模拟网络通信中的服务器端
 */
public class SocketServer {
	//这里为了代码 简洁 直接抛异常
	public static void main(String[] args) throws IOException {
		//打开服务器端的接口 等待 客户端通过 ip+端口号 来访问
		ServerSocket server = new ServerSocket( 8888 );
		
		//这一步数阻塞的，当客户端和服务器端建立连接成功时 返回 Socket s
		//才能 执行后续代码
		Socket s = server.accept();
		
		//能到这里说明 服务器端 与某个 客户端建立了连接
		System.out.println( "与某个客户端连接成功" );
		//获取 输出流
		OutputStream out = s.getOutputStream();
		//发送 一则消息 给用户
		out.write( "你好用户".getBytes( "UTF-8" ) );
		s.close();	
		server.close();
	}
}
