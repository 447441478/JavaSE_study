package cn.hncu.javaSE.junit.myJUnit;

import java.lang.reflect.Method;

public class MyJUnit {
	
	public static void main(String[] args) throws Exception {
		//首先需知道被测的 类模板
		Class<?> clazz = Class.forName( "cn.hncu.javaSE.junit.myJUnit.Demo" );
		//获取 该类模板对象中的所有 方法
		Method[] ms = clazz.getDeclaredMethods();
		//遍历 所有方法
		for (Method method : ms) {
			//如果有@MyTest 注解 就执行
			if ( method.isAnnotationPresent( MyTest.class ) ) {
				method.invoke( clazz.newInstance(), new Object[0] ); //AC
			}
		}
		
	}
}
