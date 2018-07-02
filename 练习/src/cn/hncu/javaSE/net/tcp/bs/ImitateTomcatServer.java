package cn.hncu.javaSE.net.tcp.bs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 2018年5月12日 上午11:11:56
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	模仿Tomcat服务器：
 *		响应用户请求，并发送一个HTML的格式内容给用户
 */
public class ImitateTomcatServer {
	
	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket( 8888 );
			Socket s = server.accept();
			//先记录 IP
			String ip = s.getInetAddress().getHostAddress();
			System.out.println( ip + "发来请求！");
			//※※※必须接收请求内容才能 发送响应※※※
			System.out.println( "输出请求内容：");
			InputStream in = s.getInputStream();
			byte[] buf = new byte[1024];
			int len = in.read( buf );
			System.out.println( new String( buf, 0, len ) );
			
			//获去输出流，给用户响应
			OutputStream out = s.getOutputStream();
			PrintWriter pw = new PrintWriter( out, true );
			//需要 响应头 + 响应体
			//响应头
			pw.println("HTTP/1.1 200 OK");
			//这一句是为了告诉浏览器内容是什么格式，以及编码方式
			pw.println("Content-Type: text/html; charset=UTF-8");
			pw.println();
			//响应体
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<title>模拟访问Tomcat服务器</title>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<h1 align ='center'>OKOK</h1>");
			pw.println("</body>");
			pw.println("</html>");
			s.shutdownOutput();
			
		} catch (IOException e) {
		} finally {
			if ( server != null ) {
				try {
					server.close();
				} catch (IOException e) {
				}
			}
		}
		
	}
}
