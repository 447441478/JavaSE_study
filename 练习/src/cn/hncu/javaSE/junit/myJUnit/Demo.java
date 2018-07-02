package cn.hncu.javaSE.junit.myJUnit;

public class Demo {

	@MyTest
	public void t1() {
		System.out.println( "111111111" );
	}

	public void t2() {
		System.out.println( "222222222" );
	}
	
	@MyTest
	public void t3() {
		System.out.println( "3333333333" );
	}
}
