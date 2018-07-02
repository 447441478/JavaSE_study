package cn.hncu.javaSE.net.udp.hello;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 * 2018年5月7日 上午10:17:59
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	演示 通过 UDP 接收信息
 * 接收:
 * 创建一个数据包 
 * DatagramPacket dp = new DatagramPacket(缓存);
 * 
 * 创建一个UDP的socket  //必须指定发送方数据包中给的端口号
 * DatagramSocket ds = new DatagramSocket(发送方数据包中指定的端口号); 
 * 
 * ds.receive(dp);//数据收到dp中  //※※※※
 * 
 * 从dp中把真正的数据解析出来 //※※※※
 */
public class UdpReceiveDemo {
	
	public static void main(String[] args) throws IOException, UnknownHostException {
		byte[] buf = new byte[1024];
		DatagramPacket dp = new DatagramPacket( buf, buf.length );
		DatagramSocket ds = new DatagramSocket( 8888, InetAddress.getByName( "113.242.133.196" ) );
		ds.receive( dp );
		//先记录发生者的地址
		InetAddress senderAddr = dp.getAddress();
		System.out.println( "信息发送者的主机名称：" + senderAddr.getHostName() );
		//解码
		System.out.println( new String( dp.getData(), 0, dp.getLength() ) );
		
		ds.close();
	}
}
