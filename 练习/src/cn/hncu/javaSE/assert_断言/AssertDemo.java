package cn.hncu.javaSE.assert_断言;

public class AssertDemo {
	public static void main(String[] args) {
		//JVM 参数 -ea 允许断言
		int x = 2;
		assert x==1:x+"不为1";
		System.out.println("ok");
	}
}
