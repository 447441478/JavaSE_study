package cn.hncu.javaSE.net.udp.chat.v2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
/**
 * 2018年5月7日 下午7:25:49
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	UDP聊天程序
 *	通过键盘录入获取要发送的信息。
 *	将发送和接收分别封装到两个线程中。
 *	接收端 线程
 */
public class SendThread implements Runnable {
	DatagramSocket ds = null; //用来发送数据报的套接字
	InetAddress receiverIp = null; //接收端的IP
	int receicerPort = -1 ; //接收端的端口号
	/**
	 * 构造方法，需要传入 用来发送 数据报的套接字--ds,接收端的Ip--receiverIp,接收端的端口号--receicerPort
	 * @param ds 发送数据报的套接字
	 * @param receiverIp 接收端的IP
	 * @param receicerPort 接收端的端口号
	 */
	public SendThread( DatagramSocket ds, InetAddress receiverIp, int receicerPort ) {
		this.ds = ds;
		this.receiverIp = receiverIp;
		this.receicerPort = receicerPort;
	}
	
	@Override
	public void run() {
		//获得控制台输入流
		Scanner sc = new Scanner( System.in );
		//通过 ctrl+z 退出发送
		while ( sc.hasNext() ) {
			//读取一行消息
			String mes = sc.nextLine();
			//当发生消息为  over! 结束发送
			if ( "over!".equals( mes ) ) {
				break;
			}
			DatagramPacket dp = null;
			try {
				//把消息进行编码
				//把 消息的字节码 、接收端Ip和接收端端口号 封装成一个 数据报 包
				byte[] buf = mes.getBytes( "UTF-8" );
				//把 消息的字节码 、接收端Ip和接收端端口号 封装成一个 数据报 包
				dp = new DatagramPacket( buf, buf.length, receiverIp, receicerPort );
				
				//通过 发送数据报的套接字 进行发送
				ds.send( dp );
			} catch (IOException e) {
				break;
			}	
		}
		sc.close();
		ds.close();
	}
	
	//测试
	public static void main(String[] args){
		DatagramSocket senderDs = null;
		try {
			//创建 发送端的 数据报套接字
			senderDs = new DatagramSocket();
		} catch (SocketException e) {
			return;
		}
		try {
			//创建一个发送端线程
			Thread sender = new Thread( 
								new SendThread( senderDs, InetAddress.getByName( "127.0.0.1" ), 8888 ) );
			//启动线程 进行 发送消息
			sender.start();
		} catch (UnknownHostException e) {
			return ;
		}
	}
}
