package cn.hncu.javaSE.net.udp.chat.v2;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class User1 {
	//这里为了代码 简洁 直接抛异常
	public static void main(String[] args) throws IOException {
		//自己用来发送数据报的端口号 10087
		DatagramSocket sendDs = new DatagramSocket(10087);
		InetAddress receiverIp = InetAddress.getByName( "127.0.0.1" );
		//对方用来接收数据报的端口号
		int receicerPort = 10001;
		Thread send = new Thread( new SendThread( sendDs, receiverIp, receicerPort ) );
		
		//自己的用来接收数据报 的 端口号 10086
		DatagramSocket receiveDs = new DatagramSocket(10086);
		Thread receive = new Thread( new ReceiveThread( receiveDs, 10086 ) ) ;
		
		send.start();
		receive.start();
	}
}
