package cn.hncu.javaSE.ioReinforce.ioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;
/**
 * 2018年5月3日 上午10:02:16
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	演示 字节 流
 */
public class IOStreamDemo {
	//认识系统的 in 和 out
	@Test 
	public void t1() throws IOException {
		//获得系统的输入流：InputStream
		InputStream in = System.in;
		//获得系统的输出流：PrintStream
		PrintStream out = System.out;
		//只有输入ctrl+z 才退出循环
		while(in.available() != -1){
			//因为系统输入输出是字节流 所以定义一个字节数据来接收输入
			byte[] buf = new byte[16];
			//记录每次读入的字节个数
			int len = in.read( buf );
			//输出从0到len 的内容
			out.write( buf, 0, len );
		}
		
		//关流应该在catch块后面的finally里面关 ，
		//这里为了流程看起来不乱 就省略了。。。
		in.close();
		out.close();
	}
	//从控制台输入内容，输出到文件中
	@Test
	public void t2() throws IOException {
		//获得控制台输入流
		InputStream in = System.in;
		//创建一个文件
		File outFile = new File( "testIO_Files/fileIn.txt" );
		//判断文件存不存在
		if ( !outFile.exists() ) {
			//获取所需父文件夹
			File dir = outFile.getParentFile();
			//预防空指针
			if ( dir != null ) {
				//如果文件不存在先把需要的父文件夹全部创建
				dir.mkdirs();
			}
			//再创建一个文件
			outFile.createNewFile();
		}
		//创建一个 文件输出流
		FileOutputStream fOut = new FileOutputStream( outFile );
		//定义一个缓冲数组
		byte[] buf = new byte[32];
		int len = -1;
		while( ( len = in.read( buf ) ) != -1 ){
			for (int i = 0; i < len; i++) {
				System.out.print(buf[i]);
			}
			//把每次输入的内容输出到文件中
			fOut.write( buf, 0, len );
		}
		
		//关流应该在catch块后面的finally里面关 ，
				//这里为了流程看起来不乱 就省略了。。。
		in.close();
		fOut.close();
	}
	//把文件中的内容输出到控制台显示
	@Test
	public void t3() throws IOException {
		//获得打印流
		PrintStream out = System.out;
		//使用上面测试的文件
		File inFile = new File( "testIO_Files/fileIn.txt" );
		//判断文件存不存在
		if ( !inFile.exists() ) {
			//获取所需父文件夹
			File dir = inFile.getParentFile();
			//预防空指针
			if ( dir != null ) {
				//如果文件不存在先把需要的父文件夹全部创建
				dir.mkdirs();
			}
			//再创建一个文件
			inFile.createNewFile();
		}
		//创建一个文件输入流
		FileInputStream fIn = new FileInputStream( inFile );
		byte[] buf = new byte[8];
		int len = -1;
		while ( ( len = fIn.read( buf ) ) != -1 ) {
			//打印到控制台
			out.write(buf, 0, len);
		}
		
		//关流应该在catch块后面的finally里面关 ，
		//这里为了流程看起来不乱 就省略了。。。
		fIn.close();
		out.close();
	}
	//测试 文件拷贝 
	@Test
	public void t4() throws IOException {
		//使用上面测试的文件
		File inFile = new File( "testIO_Files/fileIn.txt" );
		File outFile = new File( "testIO_Files/fileOut.txt" );
		//判断文件存不存在
		if ( !inFile.exists() ) {
			//获取所需父文件夹
			File dir = inFile.getParentFile();
			File dir2 = outFile.getParentFile();
			//预防空指针
			if ( dir != null ) {
				//如果文件不存在先把需要的父文件夹全部创建
				dir.mkdirs();
			}
			if ( dir2 != null ) {
				dir2.mkdirs();
			}
			//再创建一个文件
			inFile.createNewFile();
			outFile.createNewFile();
		}
		//创建一个文件输出流
		FileInputStream fis = new FileInputStream(inFile);
		//创建一个文件输出流
		FileOutputStream fos = new FileOutputStream(outFile);
		byte[] buf = new byte[16];
		int len = -1;
		while ( ( len = fis.read( buf ) ) != -1 ) {
			fos.write( buf, 0, len );
		}
		//先关fis
		fis.close();
		//再关fos
		fos.close();
		//简单说 就是 从数据输出的源头开始关，逐步关直到目标
	}
}
