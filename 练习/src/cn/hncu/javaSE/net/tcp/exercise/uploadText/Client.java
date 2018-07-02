package cn.hncu.javaSE.net.tcp.exercise.uploadText;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 2018年5月10日 上午10:52:53
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	客户端上传 “文本文件”
 */
public class Client {
	//这里为了代码 简洁 直接抛异常
	public static void main(String[] args) throws IOException {
		//尝试与服务器 “握手”--建立连接
		Socket s = new Socket( InetAddress.getByName( "127.0.0.1" ), 8888);
		//能到这里说明握手成功，进行文本文件上传
		//文件源：本地磁盘
		//目的：服务器
		//存文本，加缓存
		BufferedReader br = new BufferedReader(
								new FileReader( "a.txt" ) );
		//在网络传输中最好采用 PrintWriter 进行 输出,并且可以设置自动刷缓存，
		//注意自动刷缓存只对 println、printf或format 三个方法有用
		PrintWriter pw = new PrintWriter( s.getOutputStream(),true);
		
		//进行数据对拷
		String str = br.readLine();
		while ( str != null ) {
			pw.println( str );
			
			//如果没有设置 自动刷缓存 需要  pw.flush();
			str = br.readLine();
		}
		s.shutdownOutput();
		br.close();//不是 Socket 中的输入或输出流可以关
		//pw.close();只要还有传输数据就不能 关掉 Socket中的 输入流或输出流
		//接收 服务器 的提示：是否上传成功
		InputStream in = s.getInputStream();
		BufferedReader br2 = new BufferedReader( 
								new InputStreamReader( in, "UTF-8" ) );
		String mes = br2.readLine();
		while ( mes != null ) {
			System.out.println( mes );
			
			mes = br2.readLine();
		}
		
		in.close();
		br2.close();
		s.close();
		
	}
}
