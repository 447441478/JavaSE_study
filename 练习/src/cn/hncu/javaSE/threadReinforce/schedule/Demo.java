package cn.hncu.javaSE.threadReinforce.schedule;
/**
 * 2018年5月1日 上午11:18:14
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *  演示线程调度
 *  1.setPriority() 是相对调度
 *  
 *  2.Thread.sleep(3000);+t1.interrupt();
 *    2个配合 完成调度
 *  3.yield()调度，调度很弱。而且只有作用在相同优先级的线程之间。
 *    举例：三个家庭(每个家庭的各个成员相当于同一优先级)的人去抢东西，
 *    	   A家庭中的成员A2抢到了，  这时候yield()起作用，
 *    	   A2把抢到的东西在家庭内部又进行一次抢夺，这时谁抢到的就归谁。
 *  下面的演示具体看shareStack包中演示
 *  4.wait、notify和notifyAll
 *    wait()方法是放弃当前时间片，放弃锁，必须在synchronized里面，否则出异常
 *    notify()方法是唤醒共享同一把锁的线程池中的随机一个等待线程
 *    notifyAll()方法是唤醒共享同一把锁的线程池中的所有等待线程
 *  wait()与sleep()方法的区别
 *  sleep(): 放弃当前时间片，但是不放弃锁
 *  wait(): 放弃当前时间片,也放弃锁
 *  在线程调度中优先选择wait，因为执行效率高。
 */
public class Demo {
	public static void main(String[] args) {
		MyThread t1 = new MyThread(1);
		MyThread t2 = new MyThread(2);
		//setPriority() 是相对调度
//		t1.setPriority(5);
//		t2.setPriority(2);
		
		
		t1.start();
		t2.start();
		
		try {
			Thread.sleep(1000);
			t1.interrupt();//强制唤醒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class MyThread extends Thread{
	private int start;

	public MyThread(int start) {
		this.start = start;
	}
	
	@Override
	public void run() {
		if (start==1) {
			try {
				Thread.sleep(3000);//睡3秒
			} catch (InterruptedException e) {
				System.out.println("我被踹醒了！！");
			}
		}
		for (int i = start; i < 100; i+=2) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
	
}