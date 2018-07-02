package cn.hncu.javaSE.threadReinforce.deadLock.v1;
/**
 * 2018年5月2日 下午3:22:39
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	这种死锁是只有当线程  b 中 synchronized块还没执行时，
 *  a 线程调用了 b.join()方法，把 b 线程并入到 a 线程 
 *  此时 b线程内的还未执行的代码就在a线程中执行，但是 b 线程要执行
 *  synchronized块中的代码需要拿到 锁 才能执行，可是锁在 a线程 手上，
 *  a 还没释放 锁 ， 导致出现 死锁
 */
public class Demo {
	public static void main(String[] args) {
		Source s = new Source();
		Thread b = new Thread( new ThreadB( s ));
		Thread a = new Thread( new ThreadA( s, b ) );
		
		b.start();
		a.start();
	}
}
class Source{
	public int num = 666;
}
class ThreadA implements Runnable{
	private Source s = null;
	private Thread b;
	
	public ThreadA( Source s, Thread b ) {
		super();
		this.s = s;
		this.b = b;
	}

	@Override
	public void run() {
		System.out.println( "ThreadA 进来了" );
		synchronized ( s ) {
			try {
				b.join();
			} catch (InterruptedException e) {
			}
			System.out.println( s.num );
		}
		
	}
}
class ThreadB implements Runnable{
	private Source s = null;
	
	public ThreadB( Source s ) {
		super();
		this.s = s;
	}

	@Override
	public void run() {
		System.out.println( "ThreadB 进来了" );
		synchronized ( s ) {
			System.out.println( s.num );
		}
	}
}