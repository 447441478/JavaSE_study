package cn.hncu.javaSE.reflect.hello;

import java.lang.reflect.Method;

import org.junit.Test;

/**
 * 2018年5月17日 下午3:05:04
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	演示三种获取类模板对象的方法
 */
public class GetClass {
	
	//法1：通过对象获得类模板对象
	@Test
	public void t1(){
		String str = new String();
		//通过任意对象 都可以调用 getClass() 方法获取类模板对象
		Class<? extends String> c = str.getClass();
		//打印的是 String 的类全名
		System.out.println( c );
		System.out.println( "-----------------------------------------" );
		//获取 String 类里面定义的所有方法
		Method[] ms = c.getDeclaredMethods();
		for (Method method : ms) {
			System.out.println( method );
		}
		System.out.println( "-----------------------------------------" );
		System.out.println( "-----------------------------------------" );
		//获取 String 类或者其父类 的所有 public 方法
		Method[] ms2 = c.getMethods();
		for (Method method : ms2) {
			System.out.println( method );
		}
	}
	
	/* 法2: 任何数据类型(包括基本数据类型)都具备着一个静态的属性class，
	 * 通过它可直接获取到该类型对应的Class对象。
	 */
	@Test
	public void t2() {
		System.out.println( String.class );
		System.out.println( int.class );
	}
	
	//法3: 通过Class.forName()方法获取。
	@Test
	public void t3() {
		try {
			//通过 Class 类的静态方法 forName() 获得 类模板对象
			Class<?> c = Class.forName("java.lang.Integer");
			System.out.println( c );
			System.out.println( "-----------------------------" );
			Method[] ms = c.getDeclaredMethods();
			for (Method method : ms) {
				System.out.println( method );
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
