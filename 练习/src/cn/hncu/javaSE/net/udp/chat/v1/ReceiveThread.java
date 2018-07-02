package cn.hncu.javaSE.net.udp.chat.v1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReceiveThread implements Runnable{
	DatagramSocket ds = null;
	int port = -1;
	
	public ReceiveThread(DatagramSocket ds, int port) {
		this.ds = ds;
		this.port = port;
	}


	@Override
	public void run() {
		while ( true ) {
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket( buf, buf.length );
			try {
				ds.receive( dp );
				InetAddress senderIp = dp.getAddress();
				String mes = new String( dp.getData(), 0, dp.getLength() );
				System.out.println( senderIp.getHostAddress() + ":" + mes );
			} catch (IOException e) {
				break;
			}
		}
	}

}
