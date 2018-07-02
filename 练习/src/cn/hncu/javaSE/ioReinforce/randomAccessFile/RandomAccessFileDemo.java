package cn.hncu.javaSE.ioReinforce.randomAccessFile;

import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Test;

/**
 * 2018年5月3日 下午3:54:52
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	演示 随机访问文件
 *		数据流可以理解成静态摊在地上，
 * 		由我们根据游标(第一次开时从0开始）在指定位置更更改内容(以byte为单位),
 *		 如果位置计算不准确，那么会把旧数据破坏了。
 * 		读的时候也要精确计算出从什么位置开始读，读什么类型的数据(多长)，
 * 		否则数据读出来也是错误的
 */
public class RandomAccessFileDemo {
	
	@Test
	public void t1() throws IOException {
		RandomAccessFile raf = new RandomAccessFile( "testIO_Files/testRaf.txt", "rw" );
		raf.writeInt(100);
		raf.seek(0);
		raf.write(97);
		//查看文件显示内容为  a搀 
		//搀对应的int值是25600 正好 是100*256
		//可以得出显示时 a 是一个字节 这时还有三个字节 显示时后面补了一个字节 再显示出 搀
		raf.close();
	}
	//经过测试 可以得出：
	//RandomAccessFile 应该用来存储有序的数据，
	//可以推测 数据库 应该就是采用这种方式存储数据
	@Test
	public void t2() throws IOException {
		RandomAccessFile raf = new RandomAccessFile( "testIO_Files/testRaf.txt", "rw" );
		raf.writeInt( 100 );
		raf.writeInt( 99 );
		raf.write( 97 );
		raf.writeUTF("湖南城市学院");
		//下面会出异常，因为RandomAccessFile指针是往后走的，这是指先文件末尾
		//如果这是进行readInt() 会出现EOFException
//		int d = raf.readInt();
//		System.out.println(d);
		//应该按下面方式来读
		raf.seek(0);//设置从0位置开始读
		int d = raf.readInt();
		System.out.println(d);
		//如果不按写入的顺序读取会出现显示的数据是混乱的，虽然文件里面的数据没被改变。
//		int a = raf.read();
//		System.out.println(a);
//		int c = raf.readInt();
//		System.out.println(c);
		int c = raf.readInt();
		System.out.println(c);
//		int a = raf.read();
//		System.out.println(a);
		raf.skipBytes(1);//跳过一个字节
		System.out.println(raf.readUTF());
		
		raf.close();
	}
}
