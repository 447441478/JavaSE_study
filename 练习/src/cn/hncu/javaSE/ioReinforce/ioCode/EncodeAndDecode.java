package cn.hncu.javaSE.ioReinforce.ioCode;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

/**
 * 2018年5月6日 上午8:11:54
 * 
 * @author <a href="mailto:447441478@qq.com">宋进宇</a> 
 * 	演示编码和解码
 */
public class EncodeAndDecode {
	///////本次演示是在  UTF-8  环境下的///////
	// 演示编码--把字符串编译成字节码
	@Test
	public void encode() throws UnsupportedEncodingException {
		String str = "中国";
		print( str.getBytes() ); // 采用默认码表
		print( str.getBytes( "GBK" ) ); //采用指定码表进行编码，会有异常，为了逻辑看起来清晰直接抛
		//观察结果 可以发现 同一个字符串 通过不同码表 进行编码，最后的 码值 是不一样的
		//////////演示编码出错能否补救//////////
		byte[] bytes = str.getBytes( "ISO8859-1" );//西欧码表又称   拉丁1 
		print( bytes );
		/* 输出结果： 63 63 
		 * 可以发现 不同的 汉字 通过 拉丁1 码表 进行编码 得出的字节码 是两个相同的 码值
		 * 即使把得出的码值又通过 拉丁1 码表 进行解码，也是解析不出来的
		 */
		System.out.println( new String( bytes, "ISO8859-1" ) );
		//输出结果:??
		//综上:  编码     出错是   无法补救   的！！！
	}

	/**
	 * 打印字节数组
	 * 
	 * @param bytes
	 *            字节数组
	 */
	private void print(byte[] bytes) {
		for (byte b : bytes) {
			System.out.print( b + " " );
		}
		System.out.println();
	}

	// 演示解码--把解析字节码，把字节数组转换成字符串
	@Test
	public void decode() throws UnsupportedEncodingException {
		// 通过上面演示编码的打印结果，进行解码
		byte[] b = { -28, -72, -83, -27, -101, -67 }; //"中国"在UTF-8码表中对应的字节码
		System.out.println( new String( b ) ); // 采用默认码表
		System.out.println( new String( b, "GBK" ) ); //采用指定码表进行解码，会有异常，为了逻辑看起来清晰直接抛
		/*
		 * 输出结果：
		 * 中国
		 * 涓浗
		 * 
		 * 观察发现出现乱码了
		 */
		///////////演示解码出错能否补救//////////
		String str = "涓浗"; 
		byte[] bytes = str.getBytes( "GBK" );
		System.out.println( new String( bytes ) );
		/* 观察输出结果发现，采用指定码表去解码时，
		 * 当 解码 的 码表 与 编码 的 码表 不同时，
		 * 虽然 解析 结果错误，但是可以通过 错误的结果以解码时的码表，进行从新编码，
		 * 生成 字节码 ，然后 通过 生成的字节码 以最原先 编码的码表 进行 解码，
		 * 在输出结果就可以知道 这样是还原的，可以补救。
		 */
	}
}
