package cn.hncu.javaSE.threadReinforce.begin;

public class Demo {
	public static void main(String[] args) {
		Thread a = new Thread( new MyRun() );
		Thread b = new MyThread();
		a.start();
		b.start();
	}
}
