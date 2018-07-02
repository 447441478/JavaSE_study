package cn.hncu.javaSE.net.tcp.exercise.uploadImag;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 2018年5月10日 下午3:27:09
 * 
 * @author <a href="mailto:447441478@qq.com">宋进宇</a> 接收客户端发送过来的图片数据。 进行存储后，回馈一个
 *         上传成功字样。 支持多用户的并发访问。
 */
public class Server {
	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			// 服务器对外提供 一个 进行连接的端口
			server = new ServerSocket( 8888 );
			// 等待客户端 进行建立连接
			while (true) {
				Socket s = server.accept();
				// 能到这里说明用户连接成功
				// 为了支持多用户的并发访问，采用多线程进行处理
				new Thread(new Upload(s)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}

class Upload implements Runnable {
	private Socket s = null;

	public Upload(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		// 如果s为空直接结束
		if (s == null) {
			return;
		}
		// 先获取地址
		InetAddress clientIp = s.getInetAddress();
		
		PrintStream ps = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		// 再获取文件名称
		try {
			ps = new PrintStream( s.getOutputStream(), true );
			///////////////////注意///////////////////////////////////////
			//这里需要注意这里读取内容的大小 要与上传时定义的文件头大小一样 1024B
			bis = new BufferedInputStream( s.getInputStream() );
			byte[] b = new byte[1024];
			bis.read( b );
			int i = 0;
			for ( ; i < b.length; i++ ) {
				if ( b[i] == 10 ) {
					break;
				}
			}
			//获取文件名(采用 UTF-8 码表进行解码)
			String imagFilename = new String( b, 0, i, "UTF-8" );
			
			//System.out.println( imagFilename );
			//////////////////////////////////////////////////////////
			//每个ip都对应一个文件夹
			File imagDir = new File( "netFiles\\imag\\" + clientIp.getHostAddress() );
			if ( !imagDir.exists() ) {
				imagDir.mkdirs();
			}
			//给文件名加个前缀
			int count = 1;
			File imagFile = new File( imagDir, (count++) +"_"+ imagFilename );
			//进行文件重名处理
			while (imagFile.exists()) {
				imagFile = new File( imagDir, (count++) +"_"+ imagFilename );
			}
			imagFile.createNewFile();
			
			// 进行数据对拷
			bos = new BufferedOutputStream( 
					  new FileOutputStream( imagFile ) );
			byte[] buf = new byte[1024];// 一次拷1KB
			int len = bis.read( buf );
			while (len != -1) {
				bos.write( buf, 0, len );
				bos.flush();
				len = bis.read( buf );
			}
			bos.close();
			// 能到这里说明文件上传成功，给客户端 反馈
			ps.println("文件上传成功！");

		} catch (IOException e) {
			System.out.println(e.getMessage());
			if (ps != null) {
				ps.println("文件上传失败!!!");
				try {
					s.shutdownOutput();
				} catch (IOException e1) {
				}
			}
		} finally {
			try {
				if (s != null) {
					s.close();
				}
			} catch (IOException e) {
			}
		}
	}

}