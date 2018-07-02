package cn.hncu.javaSE.net.tcp.bs;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 2018年5月12日 上午11:18:06
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	模拟浏览器，向某个主机发送请求
 */
public class ImitateBrowser {
	//为了代码 简洁 直接抛异常
 	public static void main(String[] args) throws IOException {
 		//向我们百度官网发送 连接 请求
		Socket s = new Socket( "www.baidu.com", 80 );
		//能到这里说明  连接成功
		//发送请求
		//获取输出流
		PrintWriter pw = new PrintWriter( s.getOutputStream(), true );
		//这一句 相当于 我们通过域名访问某个网站的请求 头
		pw.println("GET / HTTP/1.1");//指明请求方式 空格 请求主页 空格 协议/版本
		pw.println();//这一句也是必须要的 用来区别   请求头 和 请求体
		//想要得到响应 最少需要上面两句
		
		//读取响应内容
		InputStream in = s.getInputStream();
		byte[] buf = new byte[1024];
		int len = in.read( buf );
		while ( len != -1 ) {
			System.out.println( new String( buf, 0, len ) );
			len = in.read( buf );
		}
		
		s.close();
	}
}
