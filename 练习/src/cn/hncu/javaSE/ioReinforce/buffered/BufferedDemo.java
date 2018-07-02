package cn.hncu.javaSE.ioReinforce.buffered;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.junit.Test;

/**
 * 2018年5月3日 上午11:24:03
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	演示 缓冲流
 *	    缓冲流其实就是一种装饰设计模式，对各种输入输出流进行功能强化
 */
public class BufferedDemo {
	//给字节流 加缓冲
	@Test
	public void t1() throws IOException {
		//创建一个字节缓冲输入流
		BufferedInputStream bis = new BufferedInputStream( System.in );
		//创建一个字节缓冲输出流
		BufferedOutputStream bos = new BufferedOutputStream( System.out );
		while ( bis.available() != -1 ) {
			byte[] buf = new byte[8];
			int len = -1;
			len = bis.read(buf);
			bos.write( buf, 0, len );
		}
		bis.close();
		bos.close();
	}
	
	//给字符流 加缓冲
	@Test 
	public void t2() throws IOException {
		//获取输入流
		Scanner sc = new Scanner(System.in);
		//给文件字符流加缓冲
		BufferedWriter bw = new BufferedWriter( new FileWriter( "testIO_Files/fileBuffered.txt" ) );
		
		while( sc.hasNext() ) {
			String str = sc.nextLine();
			bw.write( str );
			bw.newLine();
		}
		
		sc.close();
		bw.close();
	}
	//测试缓冲是否真的有加强普通流的能力
	//首先顺便搞1M字节内容写到文件中
	//再测试有没有加缓冲的读取速度
	/*
	 * 测试结果：加缓冲比不加缓冲速度快！
	 * 		     缓冲加在离数据源越近越快！
	 */
	@Test
	public void t3() throws IOException{
		FileOutputStream fos = new FileOutputStream( "testIO_Files/testBuff.txt" );
		int i = 0;
		while(i<1024*5){
			byte[] buf = new byte[1024];
			for (int j = 0; j < buf.length; j++) {
				buf[j] = (byte) j;
			}
			fos.write(buf);
			i++;
		}
		fos.close();
		////////////////没有加缓冲/////////////////////////
		long t1 = System.currentTimeMillis();
		DataInputStream df = new DataInputStream( 
							      new FileInputStream( "testIO_Files/testBuff.txt" ) );
		byte[] buf = new byte[1024];
		int len = -1;
		while( ( len = df.read( buf ) ) != -1 ){
			System.out.println( new String( buf, 0, len ) );
		} 
		long t2 = System.currentTimeMillis();
		df.close();
		//////////////////没有加缓冲/////////////////////////
		
		////////////////在最外层加缓冲//////////////////////////
		long t3 = System.currentTimeMillis();
		BufferedInputStream bdf = new BufferedInputStream( 
									  new DataInputStream( 
										  new FileInputStream( "testIO_Files/testBuff.txt" ) ) );
		buf = new byte[1024];
		len = -1;
		while( ( len = bdf.read( buf ) ) != -1 ){
			System.out.println( new String( buf ) );
		} 
		long t4 = System.currentTimeMillis();
		bdf.close();
		////////////////在最外层加缓冲//////////////////////////
		
		////////////////在中间加缓冲//////////////////////////
		long t5 = System.currentTimeMillis();
		DataInputStream dbf = new DataInputStream( 
								  new BufferedInputStream( 
									  new FileInputStream( "testIO_Files/testBuff.txt" ) ) );
		buf = new byte[1024];
		len = -1;
		while( ( len = dbf.read( buf ) ) != -1 ){
			System.out.println( new String( buf ) );
		} 
		long t6 = System.currentTimeMillis();
		dbf.close();
		////////////////在最外层加缓冲//////////////////////////
		System.out.println( "不加缓冲：" + ( t2 - t1 ) );
		System.out.println( "加在外层：" + ( t4 - t3 ) );
		System.out.println( "加在中间：" + ( t6 - t5 ) );
	}
	//测试字符
	@Test
	public void t4() throws IOException{
		long t1 = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(
								new BufferedReader( 
									new InputStreamReader( 
										new FileInputStream( "testIO_Files/test.txt" ) ) ) );
		String str = null;
		while ( ( str = br.readLine() ) != null ) {
			System.out.println( str );
		}
		long t2 = System.currentTimeMillis();
		br.close();
		/////////////////四层嵌套///////////////////////////
		long t3 = System.currentTimeMillis();
		
		BufferedReader br2 = new BufferedReader(
								 new InputStreamReader( 
									 new BufferedInputStream( 
										 new FileInputStream( "testIO_Files/test.txt" ) ) ) );
		str = null;
		while ( ( str = br2.readLine() ) != null ) {
			System.out.println( str );
		}
		long t4 = System.currentTimeMillis();
		br2.close();
		System.out.println(t2-t1);
		System.out.println(t4-t3);
	} 
}
