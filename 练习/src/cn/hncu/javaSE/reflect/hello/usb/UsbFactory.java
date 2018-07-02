package cn.hncu.javaSE.reflect.hello.usb;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * 2018年5月17日 下午3:47:17
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	生产USB 接口的 工厂
 */
public class UsbFactory {
	
	//封掉构造方法
	private UsbFactory(){}
	/**
	 * 获得一个USB接口的实现类
	 * @return USB接口的实现类
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static USB getUSB() throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		//创建 Properties 对象
		Properties p = new Properties();
		//获得配置文件的字节输入流
		InputStream in = ClassLoader.getSystemResourceAsStream( "cn/hncu/javaSE/reflect/hello/usb/USB.config" );
		//Properties 对象 加载 字节输入流
		p.load( in );
		//获取实现类的类名
		String name = p.getProperty( "name" );
		//通过Class.forName()方法创建 类模板对象
		Class<?> c = Class.forName( name );
		//获得 空参构造的构造器
		Constructor<?> constructor = c.getConstructor();
		//创建一个实例
		return (USB) constructor.newInstance();
	}
}
