package cn.hncu.javaSE.design_设计模式.decorate_装饰;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Time：2018/4/15
 * Description：
 * 装饰：
 * 	   在不对原有对象类进行修改的基础上，给一个或多个已有的类对象提供增强额外的功能。
 * 通过模仿API中的BufferedReader，进行学习装饰模式。
 * @author 宋进宇
 */
public class MyBufferedReader extends Reader {
	private Reader reader; //被加强的对象
	private char[] cbuf = new char[1024];//表示缓存区，大小为1K
	private int size = 0; //表示缓存区中字符的个数
	private int pos = 0; //表示当前读到缓存区的哪个位置
	
	//既然是加强已有类的功能，那么，我们就必须需要从构造方法中接收一个需要加强的类对象
	public MyBufferedReader( Reader reader) {
		this.reader = reader;
	}
	/**
	 * 读一个字符
	 * @return 是一个用int表示一个char字符，如果是0-65535，那么就说明还有数据，如果是-1说明没有数据了
	 * @throws IOException 
	 */
	public int read() throws IOException{//如果出异常需要抛出，把决策权留给调用者
		if(size==pos){//如果size==pos说明缓存区中数据已读完，需要从新加载数据
			size = reader.read(cbuf);
			pos=0;//从缓存区0的位置开始度读
		}
		if (size==-1) {
			return -1;
		}
		return cbuf[pos++];
	}
	//测试MyBufferedReader中的read()方法
//	public static void main(String[] args)throws IOException {//为了测试代码结构清晰，抛出理异常
//		MyBufferedReader mbr = new MyBufferedReader(new FileReader("a.txt"));
//		int c;
//		while((c=mbr.read())!=-1){
//			System.out.print((char)c);
//		}
//	}
	/**
	 * 读一行字符
	 * @return 是一个字符串，表示读一行字符串，如果返回值为null，说明已经读完。
	 */
	public String readLine() throws IOException{
		StringBuilder sb = new StringBuilder();//因为是对字符串不断修改，采用StringBuilder，比较快，且省资源
		int c;
		while((c=read())!=-1){
			char ch = (char)c;//注意需要转换
			if (ch=='\r') {//回车，去掉
				continue;
			}
			if (ch=='\n') {
				return sb.toString();
			}
			sb.append(ch);
		}
		if (sb.length()>0) {//预防最后一行没有换行符'\n'
			return sb.toString();
		}
		return null;
	}
	//测试MyBufferedReader中的readLine()方法
	public static void main(String[] args) throws IOException {//为了测试代码结构清晰，抛出理异常
		MyBufferedReader mbr = new MyBufferedReader(new FileReader("a.txt"));
		String str;
		while((str = mbr.readLine())!=null){
			System.out.println(str);
		}
	}
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		return reader.read(cbuf, off, len);
	}
	@Override
	public void close() throws IOException {
		reader.close();
	}
}
