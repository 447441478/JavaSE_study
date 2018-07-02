package cn.hncu.javaSE.net.tcp.exercise.uploadText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 2018年5月10日 上午11:10:21
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	接收客户端上传的 “文本文件”
 */
public class Server {
	//这里为了代码 简洁 直接抛异常
	public static void main(String[] args) throws IOException {
		//打开服务器 
		ServerSocket server = new ServerSocket( 8888 );
		//等待 客户端来 “握手” --建立连接
		Socket s = server.accept();
		//能到这里说明 客户端与服务器 建立了连接
		//进行接收上传的文件
		//源：客户端 
		//类型：纯文本文件 
		//目的：本地磁盘
		InputStream in = s.getInputStream();
		BufferedReader br = new BufferedReader( 
								new InputStreamReader( in, "UTF-8" ) );
		PrintWriter pw = new PrintWriter( "server.txt" );
		//进行数据对拷
		String str = br.readLine();
		while ( str != null ) {
			pw.println( str );
			pw.flush();//这里就演示没有 设置自动刷缓存 ，需手动刷缓存
			str = br.readLine();
		}
		//br.close(); 不能关！！！
		pw.close(); //不是 Socket 中的输入或输出流可以关
		//能到这里说明 文件上传 成功 给用户发送提示
		PrintWriter pw2 = new PrintWriter( s.getOutputStream(), true );
		pw2.println( "文件上传 成功！！" );
		s.shutdownOutput();
		s.close();
		server.close();
	}
}
