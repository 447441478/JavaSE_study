package cn.hncu.javaSE.threadReinforce.ticket.v1;
/**
 * 2018年5月1日 上午9:35:31
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 * 共享静态资源演示
 */
public class ShareStaticResource {
	public static void main(String[] args) {
		Thread t1 = new Thread(new SaleTicketRun());
		t1.setName("1号售票员");
		Thread t2 = new Thread(new SaleTicketRun());
		t2.setName("2号售票员");
		Thread t3 = new Thread(new SaleTicketRun());
		t3.setName("3号售票员");
		Thread t4 = new Thread(new SaleTicketRun());
		t4.setName("4号售票员");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
