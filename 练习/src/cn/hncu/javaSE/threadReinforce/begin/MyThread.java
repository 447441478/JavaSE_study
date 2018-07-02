package cn.hncu.javaSE.threadReinforce.begin;

public class MyThread extends Thread{

	@Override
	public void run() {
		System.out.println("MyThread 通过继承Thread覆盖run方法");
	}
}
