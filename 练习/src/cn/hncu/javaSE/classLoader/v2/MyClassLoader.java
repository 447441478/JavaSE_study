package cn.hncu.javaSE.classLoader.v2;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 2018年5月20日 下午5:53:19
 * @author <a href="mailto:447441478@qq.com">宋进宇</a><br/>
 *	制作我们的类加载器，可以加载 特殊路径
 */
public class MyClassLoader extends ClassLoader {
	/**
	 * 通过class文件的绝对路径加载该类到运行环境中
	 * @param name class文件的绝对路径
	 * @return 该class文件所对应的 类模板对象
	 * @throws IOException 
	 */
	public Class<?> MyFineClass(String name) throws IOException {
		FileInputStream fin = new FileInputStream( name );
		byte[] buf = new byte[1024];
		int len = fin.read( buf );
		//不知道 文件内容 大小 采用 内存流  ByteArrayOutputStream
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while ( len != -1 ) {
			baos.write( buf, 0, len);
			len = fin.read( buf );
		}
		fin.close(); //关流
		baos.close(); //关流 并刷缓存
		
		byte[] b = baos.toByteArray();
		//通过字节数组 加载类
		Class<?> cls = defineClass( null, b, 0, b.length);
		
		return cls;
	}
	
	public static void main(String[] args) throws Exception{
		MyClassLoader myLoader = new MyClassLoader();
		//这个路径是class文件存放的绝对路径
		Class<?> cls = myLoader.MyFineClass( "D:\\Myjava\\MyEclipse2016\\练习\\bin\\cn\\hncu\\javaSE\\classLoader\\v1\\Person.class" );
		Object obj = cls.newInstance();
		System.out.println( obj ); //AC
		// 调用setter 给 obj 赋值
		/*
	    Person p = (Person) obj; //WA
		p.setName( "张三" );
		p.setAge( 20 );
		System.out.println( p );
		上面这样 强转 是错误的，强转 只是 让编译时多态过，运行时多态 还是看内存的
		Person是通过AppClassLoader 加载的 ，obj是通过 MyClassLoader加载的
		两个类模板加载的 空间 不同 即 内存是不一样的 所以 即使可以强转 ，且就是同一个class文件
		但是运行时是过不了的，类强转异常。
		*/
		//实现上面功能如下
		Method m1 = cls.getDeclaredMethod( "setName", String.class );
		m1.invoke( obj, "张三" );
		Method m2 = cls.getDeclaredMethod( "setAge", int.class );
		m2.invoke( obj, 20 );
		System.out.println( obj ); //AC
	}
}
