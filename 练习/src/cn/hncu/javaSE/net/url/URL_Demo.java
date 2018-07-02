package cn.hncu.javaSE.net.url;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import org.junit.Test;
/**
 * 2018年5月7日 上午9:15:50
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	演示URL--统一资源定位
 */
public class URL_Demo {
	@Test
	public void t1(){
		//创建 URL 
		URL url = null;
		InputStream in = null;
		BufferedReader br = null;
		try {
			url = new URL( "http://www.baidu.com" );
			//获取 字节输入流
			in = url.openStream();
			//把 字节流 装换成 字符流 并加 缓冲
			br = new BufferedReader( 
									new InputStreamReader( 
										new BufferedInputStream( in ) /*这里可以指定码表*/ ) );
			//每次读一行
			String str = br.readLine() ;
			while ( str != null ){
				System.out.println( str );
				str = br.readLine();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if ( in != null ) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if ( br != null ) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	// 演示  URLConnection 
	@Test 
	public void t2() throws IOException{
		URL url =  new URL( "http://www.hncu.net" );
		URLConnection con = url.openConnection();
		//获取 编码
		String code = con.getContentEncoding();
		//获取 最后一次修改时间
		Date lastModified = new Date( con.getLastModified() );
		//是否允许用户交互
		boolean boo = con.getAllowUserInteraction();
		System.out.println( "编码：" + code + "，最后修改时间：" + lastModified.toString() +",是否允许用户交互：" + boo );
		int length = con.getContentLength();
		System.out.println( "内容长度：" + length +"B" );
	}
	//演示 InetAddress
	@Test
	public void t3() throws IOException{
		InetAddress ip = InetAddress.getByName( "www.baidu.com" );
		System.out.println( ip.getHostAddress() ); //主机地址
		System.out.println( ip.getHostName() ); //主机名称
		System.out.println( InetAddress.getLocalHost().toString() );
	}
}
