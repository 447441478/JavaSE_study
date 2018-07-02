package cn.hncu.javaSE.junit;

import org.junit.Test;

/**
 * 2018年5月20日 上午9:20:26
 * @author <a href="mailto:447441478@qq.com">宋进宇</a> 
 * 	演示 Junit
 */
public class Demo {

	/*
	 * 使用了@Test注解应该满足以下条件：
	 *  1) 必须是无参数的非静态方法。
	 *  2) 添加@Test注解的类，必须拥有一个无参数的公开构造，
	 * 	       且只能有这一个构造器(不能包含其它构造器)
	 */
	/*
	public Demo(int a) {

	}

	@Test // WA
	public void t1(int a) {
		System.out.println("111111111111");

	}

	@Test // WA
	public static void t2() {
		System.out.println("22222222222");
	}
	
	*/
	@Test
	public void t3() {
		System.out.println("333333333333");
		try {
			Integer.parseInt("abc123");
		} catch (NumberFormatException e) {
			System.out.println("数字格式错误...");
		}
	}

}
