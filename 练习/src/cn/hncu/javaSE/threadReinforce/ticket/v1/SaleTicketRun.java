package cn.hncu.javaSE.threadReinforce.ticket.v1;

/**
 * 2018年5月1日 上午9:40:31
 * 
 * @author <a href="mailto:447441478@qq.com">宋进宇</a> 买票
 */
public class SaleTicketRun implements Runnable {
	//如果共享的资源 不是基本数据类型的话，就用资源本身来当锁，
	//否则的话就制造一个平行的对象来当锁。
	private static int TicketCount = 200;// 共享的资源
	//制造一个与资源平行的对象锁
	private static Object objLock = new Object();

	@Override
	public void run() {

		while (true) {
			synchronized (objLock) {
				
				//如果TicketCount大于0说明可以卖票
				if (TicketCount>0) {
					System.out.println(Thread.currentThread().getName() + "卖出了第" + TicketCount + "张票");
					TicketCount--;
				}else{
					break;
				}
			}
		}
	}

}
