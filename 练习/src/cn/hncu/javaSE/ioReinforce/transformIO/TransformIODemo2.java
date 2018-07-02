package cn.hncu.javaSE.ioReinforce.transformIO;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import org.junit.Test;

/**
 * 2018年5月3日 下午7:14:12
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	演示字符编码转换
 */
public class TransformIODemo2 {
	//我用的MyEclipse的编码为UTF-8，所有先写一点数据到文件中，保存为GBK编码
	@Test
	public void write() throws IOException {
		////////////////编码：//////////////////////
		BufferedWriter bw = new BufferedWriter(
								new OutputStreamWriter( 
									new FileOutputStream( "testIO_Files/gbk.txt" ), "GBK" ) );
		Scanner sc = new Scanner( System.in );
		while( sc.hasNext() ) {
			String str = sc.nextLine();
			bw.write( str );
			bw.newLine();
			bw.flush();
		}
		
		sc.close();
		bw.close();
	}
	//测试在UTF-8编码环境下，读取GBK编码的文件
	@Test
	public void read() throws IOException {
		BufferedReader br = new BufferedReader(
								new InputStreamReader(
									new FileInputStream( "testIO_Files/gbk.txt" ) /*, "GBK" */ ) );
		String str = null;
		while ( ( str = br.readLine() ) != null ) {
			//可以发现中文的内容是乱码。
			System.out.println(str);
			/*
			 * 经测试 下面这中转换是不行的 因为在br.readLine()这一句时返回的String 
			 * 是通过 UTF-8 解码转换成的，如果想要通过下面这种"形式"转换就得通过字节流，
			 * 字符流是无法完成的，因为在br.readLine()这里解码时生成的字节码已经跟存储的字节码值不一样了，所有转换不过来
			 */
			System.out.println(new String( str.getBytes( "UTF-8" ), "GBK" ) );
		}
		br.close();
		//////////////演示new String( str.getBytes( "UTF-8" ), "GBK" )形式解码///////////////
		BufferedInputStream bis = new BufferedInputStream(
									  new FileInputStream( "testIO_Files/gbk.txt" ) );
		byte[] buf = new byte[8];
		int len = -1;
		while ( ( len = bis.read( buf ) ) != -1 ){
			//这里之所以能成功是因为buf里面的值跟文件中是一致的，然后通过"GBK"进行解码是可以成功的
			//但是需注意：这种解码是不稳定的，截取字节数据时，把一个完整的汉字拆开，会出现某些字段是乱码
			System.out.println( new String( buf, 0, len, "GBK" ));
		}
		bis.close();
		//////////////最好的解码方式，为了加快速度 可以套一层Buffered///////////////
		//对比第一个读取文件的 构造 观察不同 
		InputStreamReader fr = new InputStreamReader( 
								   new FileInputStream( "testIO_Files/gbk.txt" ), "GBK" );
		char[] cbuf = new char[8];
		len = -1;
		while ( ( len = fr.read(cbuf) ) != -1 ) {
			System.out.println( String.valueOf( cbuf, 0, len ) );
		}
		fr.close();
	} 
	
	@Test
	public void t3() throws IOException {
		BufferedReader br = new BufferedReader(
								new InputStreamReader( System.in ) );
		BufferedWriter bw = new BufferedWriter(
								new OutputStreamWriter(
									new FileOutputStream( "testIO_Files/br_bw.txt" ), "GBK" ) );
		String str = null;
		while ( ( str = br.readLine() ) != null ) {
			System.out.println( str );
			bw.write( str );
			bw.newLine();
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
}
