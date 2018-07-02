package cn.hncu.javaSE.threadReinforce.deadLock.v2;
/**
 * 2018年5月2日 下午3:28:49
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	这种死锁是 a线程需要资源s1,s2 b线程同样需要s1,s2 但是获取的顺序不同
 *	死锁情况：a拿到了 s1 进行业务处理 ，此时， b拿到了 s2 也进行业务处理，
 *  a 处理完有关s1的业务后需要拿到s2 继续处理 ，可是 此时 b处理完相关于
 *  s2的相关业务后 需要拿到 s1 继续处理  这是就出现，a拿着s1 在等 s2 
 *  b 拿着s2 在等s1 于是就出现了死锁
 */
public class Demo {
	public static void main(String[] args) {
		Source1 s1 = new Source1();
		Source2 s2 = new Source2();
		Thread a = new Thread(new ThreadAA( s1, s2 ));
		Thread b = new Thread(new ThreadBB( s1, s2 ));
		a.start();
		b.start();
	}
}
class Source1{
}
class Source2{
}
class ThreadAA implements Runnable{
	private Source1 s1;
	private Source2 s2;
	
	public ThreadAA( Source1 s1, Source2 s2 ) {
		super();
		this.s1 = s1;
		this.s2 = s2;
	}

	@Override
	public void run() {
		System.out.println( "ThreadAA 进来了" );
		synchronized ( s1 ) {
			System.out.println( "ThreadAA 拿到s1资源，还需要s2资源" );
			try {
				Thread.sleep( 2 );
			} catch (InterruptedException e) {
			}
			synchronized ( s2 ) {
				System.out.println( "ThreadAA 都拿到了" );
			}
		}
	}
}
class ThreadBB implements Runnable{
	private Source1 s1;
	private Source2 s2;
	
	public ThreadBB( Source1 s1, Source2 s2 ) {
		super();
		this.s1 = s1;
		this.s2 = s2;
	}

	@Override
	public void run() {
		System.out.println( "ThreadBB 进来了" );
		synchronized ( s2 ) {
			System.out.println( "ThreadBB 拿到s2资源，还需要s1资源" );
			try {
				Thread.sleep( 2 );
			} catch (InterruptedException e) {
			}
			synchronized ( s1 ) {
				System.out.println( "ThreadBB 都拿到了" );
			}
		}
	}
}