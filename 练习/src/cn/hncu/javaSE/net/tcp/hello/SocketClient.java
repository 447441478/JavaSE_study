package cn.hncu.javaSE.net.tcp.hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
/**
 * 2018年5月10日 上午10:48:32
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	通过 Socket 模拟网络通信中的客户端
 */
public class SocketClient {
	//这里为了代码 简洁 直接抛异常
	public static void main(String[] args) throws IOException {
		//这一句是尝试着 跟 服务器端 建立连接
		Socket s = new Socket( InetAddress.getByName( "127.0.0.1" ), 8888 );
		//能到这里说明可以进行 数据传输
		System.out.println( "连接服务器成功" );
		//获取 Socket 中的输入流
		InputStream in = s.getInputStream();
		BufferedReader br = new BufferedReader( 
								new InputStreamReader( in, "UTF-8" ) );
		//接收 服务器端 发来的信息
		System.out.println( br.readLine() );
		s.close();
	}
}
