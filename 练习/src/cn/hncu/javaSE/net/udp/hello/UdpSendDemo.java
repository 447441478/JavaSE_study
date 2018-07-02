package cn.hncu.javaSE.net.udp.hello;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 2018年5月7日 上午10:07:32
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	演示通过 UDP 发送信息
 * UDP编程就两个类：DatagramPacket，DatagramSocket
 * 数据包DatagramPacket类的构造方法分成两类：
 * 1)有地址参数(通常是Ip地址)的就是用于发送的
 * 2)没地址参数的就是用于接收的
 * 
 * DatagramSocket类的构造方法分成两类：
 * 1)有地址参数(通常是Ip地址)的就是用于接收的
 * 2)没地址参数的就是用于发送的(可以指定端口号)
 * 
 * 发送:
 * 创建一个数据包 
 * DatagramPacket dp = new DatagramPacket(数据+IP+端口);
 * 
 * 创建一个UDP的socket  //可以指定端口，也可由系统随机分配
 * DatagramSocket s = new DatagramSocket(); 
 * 
 * s.send(dp);//发送出去 ※※※入口※※※
 * 
 */
public class UdpSendDemo {
	
	public static void main(String[] args) throws IOException {
		byte[] buf = "Hello UDP ...".getBytes();
		DatagramPacket dp = new DatagramPacket( buf, buf.length, InetAddress.getByName( "113.242.133.196" ), 8888 );
		DatagramSocket ds = new DatagramSocket();
		ds.send( dp );
		ds.close();
	}
}
