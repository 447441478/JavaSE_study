package cn.hncu.javaSE.ioReinforce.othersIo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

/**
 * 2018年5月5日 下午2:55:48
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	演示采用 SequenceInputStream 把 多个流合并
 */
public class SequenceIO_Demo {
	
	public static void main(String[] args) {
		ArrayList<FileInputStream> list = new ArrayList<FileInputStream>();
		SequenceInputStream sis = null;
		try {

			//先在3个文件中自己手写些数据，做下区别以便观察
			list.add( new FileInputStream( "testIO_Files\\1.txt" ) );
			list.add( new FileInputStream( "testIO_Files\\2.txt" ) );
			list.add( new FileInputStream( "testIO_Files\\3.txt" ) );
			//采用 Collections 集合的工具类 把 集合 转换成 Enumeration 类型
			Enumeration<FileInputStream> en = Collections.enumeration( list );
			//在使用 SequenceInputStream 把3个文件输出流 合并成一个输出出口
			sis = new SequenceInputStream( en );
			//直接打印在控制台
			byte[] buf = new byte[128];
			int len = -1;
			while ( ( len = sis.read( buf ) ) != -1 ) {
				//这里需注意：因为3个文件中是自己手写的一些数据 ，所以是GBK的编码，
				//即这里需要通过GBK进行解码，但是需注意这种形式解码有风险的。
				System.out.println( new String( buf, 0, len, "GBK" ) );
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if ( sis != null) {
				try {
					sis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
