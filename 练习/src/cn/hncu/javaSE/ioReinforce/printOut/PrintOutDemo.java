package cn.hncu.javaSE.ioReinforce.printOut;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

/**
 * 2018年5月5日 下午2:01:33
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 * 	 演示打印字节流和打印字符流
 *  	PrintStream、PrintWriter中的输出有两个方法:
 * 		write() ----和字节输出流一样，以字节为单位输出，是数据的原样输出--值--面向计算机
 * 		print() ----把数据转换成字符串输出，不是数据的原样输出--值的表现形式---面向用户的
 * 		打印流在构造时可以指定字符编码 
 * 		在创建时可以指定为自动刷缓存, 只对println、printf 或 format方法有效
 */
public class PrintOutDemo {
	//演示普通的构造方法，以及常用函数
	@Test
	public void t1() {
		PrintStream ps = new PrintStream( System.out );
		ps.print( "你好" );
		ps.println( "Holle" );
		ps.write( 97 );//这里打印输出的是'a' 并不像上面print函数那样原样输出，
					 //由此可知：write打印的是真正的数据，面向计算机，通常用来数据传输
							 //print打印的是数据的表示形式，面向用户，通常用来传递信息
		//ps.flush();//这里如果不flush() 则打印不出来'a',由此可知：打印字节流带缓冲
	}
	// 演示带编码转换的构造函数
	@Test
	public void t2(){
		PrintStream ps = null;
		try {
			//我的MyEclipse设置的工作环境是UTF-8编码的，通过PrintStream
			//可以实现编码转换。
			ps = new PrintStream( "testIO_Files\\print.txt", "GBK" );
			ps.print( "中文" );
			ps.println( "湖南城市学院" );
			ps.write( 353 );//同时这里可以发现结果显示是 'a' 由此可知 字节流只打印 一个字节，
							//若超过了一个字节只打印最后面的一个字节数据
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			if ( ps != null ) {
				ps.close();
			}
		}
	}
	//通过 演示 打印字符流来测试 自动行刷新 即   autoFlush == true
	@Test
	public void t3(){
		PrintWriter pw = new PrintWriter( System.out , true );
		//我们可以发现 PrintWriter 和  PrintStream 在打印时 是不抛异常的
		//如果是别的系列的流是会抛异常的。
		pw.print( "中国" );
		pw.print( "湖南\n" );//对于print函数即使加 '\n' 换行也无法触发 自动行刷新
		//观察控制台 明明程序都执行完毕了，而且我们还设置了 自动行刷新 可是为什么控制台没有打印？
		//pw.println();//加上这一句试试，观察发现 控制台打印出来了
		//由此可知 在 用到 自动行刷新  这个属性时只有 println、printf 或 format方法有效
	}
}
