package cn.hncu.javaSE.ioReinforce.readWrite;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

/**
 * 2018年5月3日 上午10:58:31
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	演示 字符流
 */
public class ReadWriteDemo {
	
	//通过控制台输入 输出到文件中
	/* 通过观察文件内容可以发现 汉字 出现乱码问题了
	 * 原因：MyEclipse我设置的是UTF-8编码，一个汉字的是3个字节
	 *     而我定义的buf是8个字节，这时在第3个汉字时只取了2个字节
	 *     进行转换成字符串，于是就出现了乱码。
	 * 解决方法有两个：一是把buf定义足够大，但是不现实。不可取
	 * 			       另一个是采用转换流 具体看 转换流 演示
	 */
	@Test 
	public void t1() throws IOException {
		//获得输入流
		InputStream in = System.in;
		//获得文件字符输出流
		FileWriter fw = new FileWriter( "testIO_Files/fileWrite.txt" );
		byte[] buf = new byte[8];
		int len = -1;
		while( ( len = in.read( buf ) ) != -1 ){
			//这里不能像字节流那样直接写进文件，需要转换
			String str = new String( buf, 0, len);
			fw.write(str);
		}
		//关流
		in.close();
		fw.close();
	}
	
	//通过单纯的字符流实现 文件拷贝 
	//只能拷贝跟运行环境编码相同的文件，如果编码不同就会出现 乱码
	@Test
	public void t3() throws IOException {
		//被拷贝的文件直接采用 演示字节流中产生的文件
		File inFile = new File( "testIO_Files/fileIn.txt" );
		//创建一个文件字符输入流
		FileReader fr = new FileReader( inFile );
		//创建一个文件字符输出流
		FileWriter fw = new FileWriter( "testIO_Files/fileWrite.txt" );
		char[] cbuf = new char[16];
		int len = -1;
		while ( ( len = fr.read( cbuf ) ) != -1 ) {
			fw.write( cbuf, 0, len );
		}
		//关流
		fr.close();
		fw.close();
	}
	
}
