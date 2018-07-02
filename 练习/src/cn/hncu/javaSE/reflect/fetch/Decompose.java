package cn.hncu.javaSE.reflect.fetch;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;

/**
 * 2018年5月19日 上午9:09:33
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	分解 构造器、方法和属性
 */
public class Decompose {
	//分解构造器(构造方法)
	@Test //为了代码简洁，直接抛异常
	public void fetchConstructor() throws Exception{
		//获得类模板对象
		Class<?> c = Class.forName( "cn.hncu.javaSE.reflect.fetch.User" );
//		//获得该类模板以及其父类模板 的 所有public 的构造器。
//		Constructor<?>[] cons = c.getConstructors();
		
		//获得所有该类模板定义（包括 private ）的 构造器。
		Constructor<?>[] cons = c.getDeclaredConstructors();
		for (Constructor<?> constructor : cons) {
			//获得修饰符，如：public、static、final...
			//这里是用一个 整形数 来表示 修饰符，没一位代表一个修饰符
			int mod = constructor.getModifiers();
			System.out.println( "Modifiers_int：" + mod );
			System.out.println( "Modifiers_String：" + Modifier.toString( mod ) );
			
			//打印 构造器的名称
			System.out.println( "名称：" + constructor.getName() );
			
			//获得 参数的类型
			Class<?>[] parameterTypes = constructor.getParameterTypes();
			int i = 1;
			for (Class<?> clazz : parameterTypes) {
				System.out.println( "parameterTypes" + (i++) + "：" + clazz );
			}
			
			/*
			 * 还可以 获得 异常的泛型 参数的泛型 
			 * constructor.getGenericExceptionTypes();
			 * constructor.getGenericParameterTypes();
			 */
			
			System.out.println("--------------------------------------");
		}
	}
	
	//分解方法
	@Test //为了代码简洁，直接抛异常
	public void fetchMethod() throws Exception{
		//获得类模板对象
		Class<?> cls = Class.forName( "cn.hncu.javaSE.reflect.fetch.User" );
		
//		//获得该类模板以及其父类模板 的 所有public 的方法。
//		Method[] methods = cls.getMethods();
		
		//获得该类模板自身定义 的 所有方法( 包括private)。
		Method[] methods = cls.getDeclaredMethods();
		for ( int i = 0; i < methods.length; i++ ) {
			Method method = methods[i];
			//获得修饰符
			int mod = method.getModifiers();
			System.out.println( "Modifiers: " + Modifier.toString( mod ) );
			
			//获得 返回参数类型 --- 和构造器 唯一的不同
			Class<?> returnType = method.getReturnType();
			System.out.println( "ReturnType: " + returnType );
			
			//获得方法名
			System.out.println( "方法名：" +method.getName() );
			
			//获得参数类型
			Class<?>[] parameterTypes = method.getParameterTypes();
			System.out.print( "ParameterTypes: " );
			for (Class<?> c : parameterTypes) {
				System.out.print( c + " ");
			}
			System.out.println();
			
			//获得异常的类型
			Class<?>[] exceptionTypes = method.getExceptionTypes();
			System.out.print("ExceptionTypes: ");
			for (Class<?> c : exceptionTypes) {
				System.out.print( c + " " );
			}
			System.out.println();
			
			/*
			 * 还可以 获得 异常的泛型 参数的泛型  返回值的泛型
			 * method.getGenericExceptionTypes();
			 * method.getGenericParameterTypes();
			 * method.getGenericReturnType();
			 */
			
			
			System.out.println("------------------------------------------");
		}
		
	}
	
	//分解属性
	@Test //为了代码简洁，直接抛异常
	public void fetchField() throws Exception{
		//获得类模板对象
		Class<?> clazz = Class.forName( "cn.hncu.javaSE.reflect.fetch.User" );
//		//获得该类模板以及其父类模板 的 所有public 的属性。
//		Field[] fields = clazz.getFields();
		
		//获得该类模板自身定义 的 所有属性( 包括 private )。
		Field[] fields = clazz.getDeclaredFields();
		
		for (Field field : fields) {
			//获得修饰符
			int mod = field.getModifiers();
			System.out.println( "Modifiers: " + Modifier.toString( mod ) );
			
			//获得参数类型
			Class<?> type = field.getType();
			System.out.println( "Type: " + type );
			
			//获得参数名
			System.out.println( "参数名：" + field.getName() );
			
			/*
			 * 可以获得参数的泛型
			 * field.getGenericType();
			 */
			
			
			System.out.println("---------------------------------");
		}
	}
}
