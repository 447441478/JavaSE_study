package cn.hncu.javaSE.design_设计模式.singleCase_单例;

import org.junit.Test;

public class Demo {
	//测试1  单例  法1---饿汉模式
	@Test
	public void testHungryManModel() {
		HungryManModel hmm = HungryManModel.getInstance();
		System.out.println(hmm.hashCode());//340870931
		new A().fun();//340870931
		//结果：两个hashCode 值相同，实现单例
	}
	//测试2 单例 法2---懒汉模式
	@Test
	public void testLazyManModel(){
		LazyManModel lmm = LazyManModel.getInstance();
		System.out.println(lmm.hashCode());//1734172372
		new B().fun();//1734172372
		//结果：两个hashCode 值相同，实现单例
	}
	
	//测试3 单例 法1---多线程下的饿汉模式
//	public static void main(String[] args) {
//		A[] as = new A[20];
//		for (int i = 0; i < as.length; i++) {
//			as[i] = new A();
//		}
//		for (int i = 0; i < as.length; i++) {
//			as[i].start();
//		}
//		//经过几次运行会发现，所有的hashCode相同
//	}
	//测试4 单例 法2---多线程下的懒汉模式
	public static void main(String[] args) {
		B[] bs = new B[20];
		for (int i = 0; i < bs.length; i++) {
			bs[i] = new B();
		}
		for (int i = 0; i < bs.length; i++) {
			bs[i].start();
		}
		//经过几次运行会发现，会出现不相同的hashCode，
		//因为多线程的原因，会出现第一线程还没执行完
		//lazyManModel = new LazyManModel();语句
		//下一个线程就启动了，并且进入到if语句里面，从而出现不相同的hashCode
		//★★★解决方案很简单，在getInstance()方法上加锁。
	}
}
class A extends Thread{
	@Override
	public void run() {
		fun();
	}
	
	public void fun(){
		System.out.println(HungryManModel.getInstance().hashCode());
	}
}
class B extends Thread{
	@Override
	public void run() {
		fun();
	}
	
	public void fun() {
		System.out.println(LazyManModel.getInstance().hashCode());
	}
}
