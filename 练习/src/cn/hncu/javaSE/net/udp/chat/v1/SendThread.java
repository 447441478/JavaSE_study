package cn.hncu.javaSE.net.udp.chat.v1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class SendThread implements Runnable {
	DatagramSocket ds = null;
	InetAddress receiverIp = null;
	int receicerPort = -1 ;
	
	public SendThread( DatagramSocket ds, InetAddress receiverIp, int receicerPort ) {
		this.ds = ds;
		this.receiverIp = receiverIp;
		this.receicerPort = receicerPort;
	}
	
	@Override
	public void run() {
		Scanner sc = new Scanner( System.in );
		while ( true ) {
			String mes = sc.nextLine();
			byte[] buf = mes.getBytes();
			DatagramPacket dp = new DatagramPacket( buf, buf.length, receiverIp, receicerPort );
			try {
				ds.send( dp );
			} catch (IOException e) {
				break;
			}	
		}
		sc.close();
		ds.close();
	}
	
}
