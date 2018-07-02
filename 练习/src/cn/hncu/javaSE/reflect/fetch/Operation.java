package cn.hncu.javaSE.reflect.fetch;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * 2018年5月19日 上午10:12:35
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	演示 操纵构造器、方法和属性  外加 暴力访问 private的构造器、方法和属性
 */
public class Operation {
	
	//构造器的操作(调用)
	@Test //为了代码简洁，直接抛异常
	public void operationConstructor() throws Exception{
		//1.获得类模板对象
		Class<?> c = Class.forName( "cn.hncu.javaSE.reflect.fetch.User" );
		//2.获得空参的构造器 public User()
		Constructor<?> constructor = c.getDeclaredConstructor( new Class[0] );
		//3.操作该构造器
		Object obj = constructor.newInstance( new Object[0] );
		System.out.println( obj );
		/* 上面通过类反射 获得的 一个空参实例 与传统方式：User u = new User();
		 * 相比是不是 更加麻烦，但是为什么有类反射这个机制？
		 * 答案很显然，通过类反射，我们就不会依赖 某个 未知的类，而是依赖已有的类String(解耦)
		 * 这样的话，不管要调用的类写了没有，我们都可以事先搭好框架，等具体的类完成时，
		 * 通过配置文件的方法 ，把具体的类全名注入 程序就可以跑了。
		 */
		//下面演示有参的构造器生成一个实例
		//获得有参 构造器 public User(String name, int age)
		//有参时 就涉及到 可变参数，可变参数 可以是 以一个数组 的形式传入，也可以通过具体参数的形式传入
		Class<?> parameterTypes[] = { String.class, int.class };
		Constructor<?> constructor2 = c.getConstructor( parameterTypes ); //以一个数组 的形式传入
		//创建一个有参实例
		Object obj2 = constructor2.newInstance( "Jack", 22 ); //具体参数的形式传入
		System.out.println( obj2 );
	}
	
	//方法的操作(调用)
	@Test//为了代码简洁，直接抛异常
	public void operationMethod() throws Exception {
		//1.获得类模板对象
		Class<?> clazz = Class.forName( "cn.hncu.javaSE.reflect.fetch.User" );
		//////////////演示非静态方法//////////////
		
		//非静态方法需要通过 对象 去调用该方法如：new User.sum( 100, 55.5 );
		//所以先要有一个对象
		/*
		 * Constructor<?> constructor = c.getDeclaredConstructor( new Class[0] );
		 * Object obj = constructor.newInstance( new Object[0] );
		 * 上面 这两句 跟 下面 这一句 是一样的(只有空参的才可以)
		 */
		//采用 clazz.newInstance() 空参的实例 
		Object obj = clazz.newInstance();
		
		//2.获得该类模板的方法
		Method method = clazz.getDeclaredMethod("sum", new Class[]{ int.class, double.class } );
		//3.操作该方法
		// sum() 方法有返回值，所以invoke()方法的返回值 就是sum()方法的返回值
		Object returnValue = method.invoke(obj, 100, 55.5 ); //AC
		
		//测试 自动装箱和自动拆箱 ：
		//Method method = clazz.getDeclaredMethod("sum", new Class[]{ Integer.class, double.class } ); // WA
		//Method method = clazz.getDeclaredMethod("sum", new Class[]{ Integer.TYPE, double.class } ); // AC
		//Object returnValue = method.invoke(obj, new Integer( 100 ), 55.5 ); //AC
		System.out.println( returnValue ); //结果为 155.5 是正确的
		
		/* 测试 自动装箱和自动拆箱 结果：
		 * 	在获取  Method 的时候是没有 自动装箱和自动拆箱 的，但是 包装类的 Integer.TYPE==int.class
		 * 	但是 在invoke() 时 参数 是可以 自动装箱和自动拆箱 的
		 */
		
		
		//////////////演示静态方法//////////////
		Method method2 = clazz.getMethod( "print", new Class[0] );
		//静态方法不需要 通过 对象 调用 所有 直接 用 null
		//print() 方法的返回值是 void ，所以invoke方法返回的是 null
		Object returnValue2 = method2.invoke( null, new Object[0] ); 
		System.out.println( returnValue2 );
	}
	
	//属性的操作(调用)
	@Test//为了代码简洁，直接抛异常
	public void operationField() throws Exception {
		//1.获得类模板对象
		Class<?> cls = Class.forName( "cn.hncu.javaSE.reflect.fetch.User" );
		//非静态的 属性 需要用过对象 去调用
		User obj = new User( "U001", "张飞", 99 );
		
		//2.获取类模板的属性
//		Field field = cls.getDeclaredField( "id" );
		//3.操作该属性
//		Object value = field.get( obj ); //WA 私有的成员变量 是不能在其它类中 被访问的
		//可以通过 get方法获取；也可暴力访问，待会演示
		//2.获取该类模板上的方法
		Method method = cls.getMethod("getId", new Class[0] );
		//3.操纵 该方法
		Object value = method.invoke(obj, new Object[0] );
		System.out.println( "id:" + value );
		
		//2.获取类模板的属性
		Field field = cls.getDeclaredField( "i" ); // public修饰的属性
		//3.操作该属性
		//给 obj的属性：i 赋值
		field.setInt( obj, 100 );
		//读取 obj的属性：i 的值
		Object value2 = field.get( obj );
		System.out.println( "i: " + value2 );
	}
	
	//演示 暴力访问当前类无法访问的属性
	@Test//为了代码简洁，直接抛异常
	public void accessViolence() throws Exception {
		
		User obj = new User( "U001", "张飞", 99 );
		
		//1.获得类模板对象
		Class<?> c = Class.forName( "cn.hncu.javaSE.reflect.fetch.User" );
		//2.获取该类模板上的属性
		Field field = c.getDeclaredField( "id" );
		//设置可以访问 ***************** 暴力访问的关键
		field.setAccessible( true );
		//可以读
		System.out.println( "id: " + field.get( obj ) ); //AC 暴力访问成功
		//同样可以写
		field.set( obj, "U007" );//AC 暴力修改成功
		System.out.println( "id: " + field.get( obj ) ); //AC 暴力访问成功 
		System.out.println( obj );
		//构造器、方法 都一样  setAccessible( true ) 就可以暴力访问
	}
}
