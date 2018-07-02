package cn.hncu.javaSE.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class Demo2 {
	private String str = null;

	@Before
	public void aa() {
		str = new String( "1234" ); // 初始化一个对象
		System.out.println( "aaaaaaaaaaaaa111....." );
	}

	@After
	public void bb() {
		str = null; // 释放内存
		System.out.println( "aaaaaaaaaaaaa222....." );
	}

	@BeforeClass // 该注解必须声明在静态方法上.调用时机:在使用该类模板之前 且只执行一次
	public static void c1() {
		System.out.println( "cccccccccccc1" );
	}

	@AfterClass // 该注解必须声明在静态方法上.调用时机:在该类模板最后一次使用之后 且只执行一次
	public static void c2() {
		System.out.println( "cccccccccccc2" );
	}

	@Test
	public void t1() {
		System.out.println( "111111111111::::" + str.toString() );
	}

	@Test
	public void t2() {
		str = str.substring(1);
		System.out.println("222222>>>>" + str.toString());
	}

}
