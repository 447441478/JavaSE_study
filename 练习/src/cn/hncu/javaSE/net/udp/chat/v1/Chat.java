package cn.hncu.javaSE.net.udp.chat.v1;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Chat {
	public static void main(String[] args) {
		DatagramSocket receiveDs = null;
		DatagramSocket senderDs = null;
		try {
			//System.out.println(InetAddress.getLocalHost());
			receiveDs = new DatagramSocket( 8888, InetAddress.getByName( "192.168.244.1" ) );
			senderDs = new DatagramSocket();
		} catch (SocketException e) {
			return;
		} catch (UnknownHostException e) {
			return;
		}
//		System.out.println( receiveDs.getLocalPort() );
//		System.out.println( receiveDs.getLocalAddress() );
		Thread send = new Thread( 
						  new SendThread(senderDs, receiveDs.getLocalAddress(), 8888 ) );
		Thread receive = new Thread(
							 new ReceiveThread( receiveDs, receiveDs.getLocalPort() ) );
		receive.start();
		send.start();
		
	}

}
