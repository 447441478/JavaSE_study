package cn.hncu.javaSE.design_设计模式.multiCase_多例;

import org.junit.Test;

/**
 * Time：2018/4/14
 * Description：测试 多例
 * @author 宋进宇
 */
public class Demo {
	//测试单线程
	@Test
	public void test1(){
		System.out.println("key1:"+MultiCase.getInstanceByKey("1").hashCode());
		System.out.println("key2:"+MultiCase.getInstanceByKey("2").hashCode());
		System.out.println("key1:"+MultiCase.getInstanceByKey("1").hashCode());
		System.out.println("key3:"+MultiCase.getInstanceByKey("3").hashCode());
		System.out.println("key2:"+MultiCase.getInstanceByKey("2").hashCode());
		System.out.println("key1:"+MultiCase.getInstanceByKey("1").hashCode());
		//结果：在相同key的情况下，hashCode值相同
	}
	//测试多线程
	public static void main(String[] args) {
		A as[] = new A[21];
		for (int i = 0; i < as.length; i++) {
			//测试在多线程情况下：三种key值对应的对象实例的hashCode
			as[i] = new A((i%3)+"");
		}
		for (int i = 0; i < as.length; i++) {
			as[i].start();
		}
		//测试--没有通过key加锁--的结果：
		/*
		    1:2034497288 ***出现hashCode不同
			1:201437278  ***
			0:1366504562
			0:1366504562
			2:1734172372
			2:1734172372
			0:1366504562
			2:1734172372
			1:201437278  ***
		 */
	}
}
class A extends Thread{
	private String key;
	public A(String key) {
		this.key = key;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(key+":"+MultiCase.getInstanceByKey(key).hashCode());
	}
}
