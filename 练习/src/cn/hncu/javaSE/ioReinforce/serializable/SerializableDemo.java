package cn.hncu.javaSE.ioReinforce.serializable;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

/**
 * 2018年5月3日 下午6:17:23
 * @author <a href="mailto:447441478@qq.com">宋进宇</a> 
 * 演示序列化：
 * 	 被序列化的对象必须要实现Serializable接口
 * 	 序列化时，非静态变量都会存入对象图，静态变量和函数都是不会存入对象图的。
 * 	 如果某个非静态变量不想存入对象图，则可以把它声明成瞬时变量(transient)
 */
public class SerializableDemo {

	// 需注意：通过对象流写到文件的对象需要实现序列化接口，否则出异常
	@Test
	public void write() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("testIO_Files/obj.txt"));
		Person p1 = new Person("Jack", 18, "123456" );
		System.out.println( p1 );
		Person p2 = new Person("Tom", 20, "7845116" );
		System.out.println( p2 );
		Person p3 = new Person("张三", 19, "6666666" );
		System.out.println( p3 );
		oos.writeObject(p1);
		oos.writeObject(p2);
		oos.writeObject(p3);
		oos.close();
	}

	// 注意：通过对象流读取对象时，可以通过捕捉 EOFException 来判断是否读取完毕
	@Test
	public void read() throws IOException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("testIO_Files/obj.txt"));
		while (true) {
			try {
				Person obj = (Person) ois.readObject();
				System.out.println(obj);
			} catch (EOFException e) {// 出现这个异常说明文件读取完毕
				System.out.println("文件读取完毕");
				break;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		ois.close();
	}
}
