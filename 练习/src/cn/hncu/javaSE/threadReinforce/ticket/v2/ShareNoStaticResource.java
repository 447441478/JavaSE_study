package cn.hncu.javaSE.threadReinforce.ticket.v2;
/**
 * 2018年5月1日 上午10:13:40
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 * 	共享非静态资源演示
 */
public class ShareNoStaticResource {
	public static void main(String[] args) {
		SaleTicketRun saleTicketRun = new SaleTicketRun();
		//共享非静态资源就必须保持是同一份资源，即Runnable 要同一个
		//否则就没有法实现共享的效果
		Thread t1 = new Thread(saleTicketRun);
		t1.setName("1号售票员");
		Thread t2 = new Thread(saleTicketRun);
		t2.setName("2号售票员");
		Thread t3 = new Thread(saleTicketRun);
		t3.setName("3号售票员");
		Thread t4 = new Thread(saleTicketRun);
		t4.setName("4号售票员");
		
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
	}
}
