package cn.hncu.javaSE.net.udp.chat.v2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
/**
 * 2018年5月7日 下午7:25:43
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	UDP聊天程序
 *	通过键盘录入获取要发送的信息。
 *	将发送和接收分别封装到两个线程中。
 *	发送端 线程
 */
public class ReceiveThread implements Runnable{
	DatagramSocket ds = null; //接收端的 数据报套接字
	int port = -1; //接收端的端口号
	/**
	 * 构造方法,传入接收端的 数据报套接字--ds和接收端的端口号--port
	 * @param ds 数据报套接字
	 * @param port 端口号
	 */
	public ReceiveThread(DatagramSocket ds, int port) {
		this.ds = ds;
		this.port = port;
	}


	@Override
	public void run() {
		//只要 发送端 有发送消息 接收端就处理
		while ( true ) {
			//创建一个用来接收的数据报的字节数组
			byte[] buf = new byte[1024];
			//封装 一个 接收 数据报 的包
			DatagramPacket dp = new DatagramPacket( buf, buf.length );
			try {
				//等待 发送端发送消息
				ds.receive( dp ); //这里是阻塞的
				//能到这里 说明发送端发来消息了
				//先获取 发送端的 ip
				InetAddress senderIp = dp.getAddress();
				//把 消息的字节数组 进行 解析
				String mes = new String( dp.getData(), 0, dp.getLength(), "UTF-8" );
				//在控制台 打印 发送端的地址 和 消息内容
				System.out.println( senderIp.getHostAddress() + ":" + mes );
			} catch (IOException e) {
				break;
			}
		}
	}
	
	//测试
	public static void main(String[] args){
		DatagramSocket receiveDs = null;
		try {
			//创建一个 接收 数据报的套接字
			receiveDs = new DatagramSocket( 8888 );
		} catch (SocketException e) {
			return;
		}
		//创建一个 接收端线程
		Thread receicer = new Thread( 
							  new ReceiveThread( receiveDs , receiveDs.getLocalPort() ) );
		//启动 接收端线程
		receicer.start();
	}
}
