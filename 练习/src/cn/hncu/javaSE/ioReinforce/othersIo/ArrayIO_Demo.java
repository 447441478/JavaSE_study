package cn.hncu.javaSE.ioReinforce.othersIo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 2018年5月5日 下午2:43:20
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	演示内存流
 *		用于读取未知的数据大小
 */
public class ArrayIO_Demo {
	
	public static void main(String[] args) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream( "中文".getBytes() );
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int b = -1;
		while ( ( b = bais.read() ) != -1 ){
			baos.write(b);
		}
		System.out.println( baos.toString() );
	}
}
