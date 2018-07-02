package cn.hncu.javaSE.net.tcp.exercise.uploadImag;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * 2018年5月10日 下午3:03:07
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	把一个图片文件发送到服务端并读取回馈信息。
 *	要求判断文件是否存在及格式是否为jpg或gif并要求文件小于2M
 */
public class Client {
	//为了 代码简洁 直接抛异常
	public static void main(String[] args) throws IOException {
		//先判断是否符合上传的要求
		//要求1：文件是否存在
		//要求2：格式是否为jpg或gif
		//要求3：文件小于2M
		JFileChooser jfc = new JFileChooser();
		int sel = jfc.showOpenDialog( null );
		File imagFile = null;
		if ( sel == JFileChooser.APPROVE_OPTION ) {
			imagFile = jfc.getSelectedFile();
		}
		if ( imagFile == null ) {
			return ;
		}
		//如果文件不存在,就结束
		if ( !imagFile.exists() || imagFile.isDirectory() ) {
			JOptionPane.showMessageDialog(null, "文件不存在" );
			return;
		}
		//能到这里说明文件存在，接下来进行格式校验
		//如果不是 jpg或gif 就结束
		if ( !( imagFile.getName().endsWith( ".jpg" ) || imagFile.getName().endsWith( ".gif" ) ) ) {
			JOptionPane.showMessageDialog(null, "文件格式不符合,只能是jpg或者gif格式" );
			return;
		}
		//能到这里说明上面的要求符合，进行最后的校验
		if ( imagFile.length() >= 2*1024*1024 ) {
			JOptionPane.showMessageDialog(null, "文件太大" );
			return;
		}
		
		//与服务器 建立连接
		Socket s = new Socket( "113.242.149.32", 8888 );
		//能到这里 说明 已经建立连接
		
		//程序能到这里说明可以进行上传了
		//因为文件是 图片 类型 为了不失真 采用字节流
		//考虑到速度问题 可以加个缓存
		BufferedOutputStream bos = new BufferedOutputStream( s.getOutputStream() );
		//先发送文件名
		//这里需要定义一个协议--文件名协议 ，就是 文件名长度不能超过 1024-1 个字节
		byte[] fileName = new byte[1024];
		//采用 UTF-8 码表进行编码
		byte[] bytes = ( imagFile.getName() + "\n" ).getBytes( "UTF-8" );
		if ( bytes.length > 1024 ) {
			JOptionPane.showMessageDialog(null, "文件名长度太长！必须小于1023个字节");
		}
		//采用系统的数组拷贝函数
		System.arraycopy( bytes, 0, fileName, 0, bytes.length );
		
		bos.write( fileName, 0, 1024 );
		bos.flush();//采用缓存流需要刷缓存，不然有BUG
		
		BufferedInputStream bis = new BufferedInputStream(
									  new FileInputStream( imagFile ) );
		
		//再数据对拷
		byte[] buf = new byte[1024]; //一次读1KB
		int len = bis.read( buf );
		while ( len != -1) {
			bos.write( buf, 0, len );
			bos.flush(); //采用缓存流需要刷缓存，不然有BUG
			len = bis.read( buf );
		}
		//关闭  输入流
		bis.close();
		//并且做一个标识--文件接收标识
		s.shutdownOutput();
		//接收 服务器端 反馈
		BufferedReader br = new BufferedReader(	
								new InputStreamReader( s.getInputStream(), "UTF-8" ) );
		//读取 服务器反馈的内容
		String mes = br.readLine();
		JOptionPane.showMessageDialog( null, mes );
		
		s.close();
	}
}
