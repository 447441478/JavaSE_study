package cn.hncu.javaSE.ioReinforce.transformIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 2018年5月3日 下午6:57:06
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	演示把字节流转换成字符流
 */
public class TransformIODemo1 {
	public static void main(String[] args) throws IOException {
		/* 需求：模拟英文聊天程序，要求：
	 		(1) 从键盘录入英文字符，每录一行就把它转成大写输出到控制台；
	 		(2) 保存聊天记录到字节流文件。
		 */
//		//1.获取控制台输入流
//		InputStream in = System.in;
//		//2.把字节流转换成字符流
//		InputStreamReader isr = new InputStreamReader( in );
//		//3.需要拥有一下读取一行的能力，采用套接一层BufferedReader
//		BufferedReader br = new BufferedReader( isr );
		//一气呵成
		BufferedReader br = new BufferedReader(
								new InputStreamReader( System.in ) );
		BufferedWriter bw = new BufferedWriter(
								new OutputStreamWriter( 
									new FileOutputStream( "testIO_Files/chat.txt" ) ) );
		String mes = null;
		while ( ( mes = br.readLine() ) != null ) {
			System.out.println( mes.toUpperCase() );
			bw.write(mes);
			//因为要保存成一行一行的形式，需要newLine，不然挤在一起
			//可以通过bw.write(mes+"\r\n"); 来换行，但是跨平台性不好。
			//比如Window系统和Linux系统，两个系统是不一样的换行风格
			bw.newLine();
			//因为有缓冲流缘故，为了数据实时性更新，需要刷一下
			bw.flush();
		}
		br.close();
		bw.close();
	}
}
