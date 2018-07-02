package cn.hncu.javaSE.ioReinforce.ioCode;

import java.io.UnsupportedEncodingException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

/**
 * 2018年5月6日 上午8:55:51
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	练习：字符串截取
		在java中，字符串“abcd”与字符串“ab你好”的长度是一样，都是四个字符。
		但对应的字节数不同，一个汉字占两个字节。
		定义一个方法，按照指定的字节数来取子串。
		如：对于“ab你好”，如果取三个字节，那么子串就是ab与“你”字的半个，
		那么半个就要舍弃。如果取四个字节就是“ab你”，取五个字节还是“ab你”。
 */
public class CutString {
	//先测试一下 在不同 码表 下汉字对应的 字节码
	//包含 汉字 的 常用码表 有： "GBK" 和  "UTF-8" 两种
	@Test
	public void testCode() throws UnsupportedEncodingException{
		String str = "ab你好";
		print( str.getBytes( "GBK" ) ); //一个汉字两字节，且码值全为负
		print( str.getBytes( "UTF-8" ) ); //一个汉字三个字节，且码值全为负
		//测试非常陌生的 汉字 
		print( "鯡".getBytes( "GBK" ) ); //一个汉字两字节，第一字节为负，第二个为正
		print( "鯡".getBytes( "UTF-8" ) ); //一个汉字三个字节，且码值全为负
		/* 综上：GBK码表中一个汉字两字节，第一个字节为负，第二个字节不一定
		 * 	   UTF-8码表中一个汉字三个字节，且码值全为负
		 */
		
	}
	/**
	 * 打印字节数组
	 * @param bytes 字节数组
	 */
	private void print(byte[] bytes) {
		for (byte b : bytes) {
			System.out.print( b + " " );
		}
		System.out.println();
	}
	
	@Test
	public void testGBK() throws UnsupportedEncodingException{
		String str = "ab你好鯡a汉字12";
		for (int i = 0; i <= str.getBytes( "GBK" ).length; i++) {
			System.out.println( cutStringInGBK( str, i ) );
		}
	}
	private static String cutStringInGBK(String str,int len){
		//如果被切割的字符串为null 就返回 null
		if ( str == null ) {
			return null;
		}
		//先把字符串编译成字节码
		try {
			byte[] bytes = str.getBytes( "GBK" );
			/* 同观察 GBK 码表 的字节码 规律可以得出 ：
			 * 可以从字节数组的len-1开始找，并且统计字节值负个数，
			 * 找到第一个字节值为非负数时就停止，如果统计的个数为奇数 说明 要舍弃最后一个字节，否则就不用舍弃
			 */
			//如果 切割的长度小于0 则返回"";
			if ( len < 0 ) {
				return "";
			}
			//如果 切割的长度大于 字节数组 则返回原字符串
			if ( len > bytes.length ) {
				return str;
			}
			int count = 0;
			for (int i = len-1; i >= 0; i--) {
				if (bytes[i]<0) {
					count++;
				} else {
					break;
				}
			}
			return new String( bytes, 0, len-(count%2), "GBK" );
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException( "该字符串不支持通过GBK码表进行编码", e );
		}
	}
	
	private static String cutStringInUTF8(String str,int len){
		//过程跟 GBK码表下 差不多
		//如果被切割的字符串为null 就返回 null
		if ( str == null ) {
			return null;
		}
		//先把字符串编译成字节码
		try {
			byte[] bytes = str.getBytes( "UTF-8" );
			//如果 切割的长度小于0 则返回"";
			if ( len < 0 ) {
				return "";
			}
			//如果 切割的长度大于 字节数组 则返回原字符串
			if ( len > bytes.length ) {
				return str;
			}
			int count = 0;
			for (int i = len-1; i >= 0; i--) {
				if (bytes[i]<0) {
					count++;
				} else {
					break;
				}
			}
			return new String( bytes, 0, len-(count%3), "UTF-8" );
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException( "该字符串不支持通过UTF-8码表进行编码", e );
		}
	}
	//查看 系统 配置信息
	@Test
	public void testSystemPropertys(){
		Properties properties = System.getProperties();
		Set<Entry<Object, Object>> entrySet = properties.entrySet();
		for (Entry<Object, Object> entry : entrySet) {
			System.out.println( entry );
		}
	}
	/**
	 * 字符串截取 ，如果不是完整的汉字就舍弃
	 * @param str 被截取的字符串
	 * @param len 截取的长度
	 * @return 截取后的字符串
	 */
	public static String cutString(String str,int len){
		//防护 空指针异常
		if ( str == null ) {
			return null;
		}
		
		if ( System.getProperty( "file.encoding" ).equalsIgnoreCase( "GBK" ) ) {
			 return cutStringInGBK( str, len );
		} else if ( System.getProperty( "file.encoding" ).equalsIgnoreCase( "UTF-8" ) ) {
			return cutStringInUTF8( str, len );
		}
		return "";
	}
	
	public static void main(String[] args) {
		String str = "ab你好鯡asd中文1223汉字sadsa";
		for (int i = 0; i < str.getBytes().length; i++) {
			System.out.println( "len=" + i + ":" + cutString( str, i ) );
		}
	}
}
