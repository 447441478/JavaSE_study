package cn.hncu.javaSE.net.chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * 
 * 2018年5月13日 上午8:55:32
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *
 */
public class ClientForm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	//服务器端的 IP 和 PORT
	private static final String IP = "127.0.0.1";
	private static final int PORT = 9999;
	
	private JTextField tfdUserName; //用户标识
	
	private JList<String> list; //在线用户列表--表现层
	private DefaultListModel<String> lm; //在线用户列表--数据层
	
	private JTextArea allMsg = new JTextArea(); //消息显示主窗口
	private JTextField tfdMsg; //发消息输入框
	
	
	private Socket socket = null;
	
	private JButton btnConn;
	
	public ClientForm() {
		setBounds( 300, 300, 400, 300 );
		
		//1上部面板
		JPanel p = new JPanel();
		
		p.add( new JLabel( "用户标识:" ) );
		tfdUserName = new JTextField( 10 );
		p.add( tfdUserName );
		btnConn = new JButton( "连接" );
		btnConn.setActionCommand( "connection" );
		btnConn.addActionListener( this );
		p.add( btnConn );
		JButton btnExit = new JButton( "退出" );
		btnExit.setActionCommand( "exit" );
		btnExit.addActionListener( this );
		p.add( btnExit );
		
		this.add( p, BorderLayout.NORTH ); //添加到上部
		
		//2中部面板
		JPanel centerP = new JPanel();
		centerP.setLayout( new BorderLayout() );
		//2.1东 在线用户列表
		lm = new DefaultListModel<String>();
		lm.addElement( "全部" );
		list = new JList<String>( lm );
		list.setSelectedIndex( 0 );
		list.setVisibleRowCount( 2 );
		JScrollPane jsc = new JScrollPane( list );
		jsc.setBorder( new TitledBorder( "在线" ) );
		jsc.setPreferredSize( new Dimension( 70, centerP.getHeight() ) );
		centerP.add( jsc, BorderLayout.EAST );
		
		//2.2中  聊天信息窗口
		allMsg.setEditable( false );
		centerP.add( new JScrollPane( allMsg ) );
		
		//2.3南  消息发送面板
		JPanel sendP=new JPanel();
		sendP.add( new JLabel( "消息:" ) );
		tfdMsg = new JTextField( 20 );
		sendP.add( tfdMsg );
		JButton btnSend = new JButton( "发送" );
		btnSend.setActionCommand( "send" );
		btnSend.addActionListener( this );
		sendP.add( btnSend );
		
		centerP.add( sendP, BorderLayout.SOUTH );
		
		this.add( centerP ); //添加到中部
		
		addWindowListener( new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				//退出前，先服务器发送退出消息
				sendExitMsg();
			}
			
		});
		
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if ( "connection".equals( e.getActionCommand() ) ) {//链接
			try {
				socket = new Socket( IP, PORT );
				//连接成功就给服务器发送用户名
				PrintWriter pw = new PrintWriter( socket.getOutputStream(), true );
				pw.println( tfdUserName.getText() );
				//把 tfdUserName 设置不可编辑，同时 把 连接按钮置灰
				tfdUserName.setEditable( false );
				btnConn.setEnabled( false );
				//同时设置一下 标题
				setTitle( "Java修仙群-在线中..." );
				
				//开一个线程接收服务器发送过来的信息
				new Thread( new Receive() ).start();;
			} catch (IOException e1) {
				JOptionPane.showMessageDialog( this, "服务器未启动..." );
				return;
			}
		} else if ( "exit".equals( e.getActionCommand() ) ) {
			//退出前向服务器发送退出消息
			sendExitMsg();
			
		} else if ( "send".equals( e.getActionCommand() ) ) {
			//如果 用户没有登入 就不能发送信息
			if ( socket == null ) {
				JOptionPane.showMessageDialog( this, "请您登入后再发送信息");
				return;
			}
			PrintWriter pw = null ;
			try {
				pw = new PrintWriter( socket.getOutputStream(), true );
			} catch (IOException e2) {
				e2.printStackTrace();
				return;
			}
			int index = list.getSelectedIndex();
			if ( index == 0 ) { //如果index==0说明是发送给所有人
				String mes = "send@#all@#" + tfdUserName.getText() + "@#" + tfdMsg.getText();
				pw.println( mes );
				//更新消息栏
				allMsg.append( "您说：" + tfdMsg.getText() + "\r\n" );
			} else {//否则 就是 私发消息
				if ( lm.get(index).equals( tfdUserName.getText() ) ) {
					JOptionPane.showMessageDialog( this, "不能跟自己私聊..." );
					return;
				}
				String mes = "send@#" + lm.get(index) + "@#" + tfdUserName.getText() + "@#" + tfdMsg.getText();
				pw.println( mes );
				allMsg.append( "您悄悄对" + lm.get(index) + "说：" + tfdMsg.getText() + "\r\n" );
			}
			//发送完毕后清空tfdMsg
			tfdMsg.setText( "" );
		}
	}

	/**
	 * 发送退出消息
	 */
	private void sendExitMsg() {
		if ( socket != null ) {
			try {
				PrintWriter pw = new PrintWriter( socket.getOutputStream(), true );
				pw.println( "exit@#all@#" + tfdUserName.getText() + "@#" );
			} catch (IOException e1) {
				e1.printStackTrace();
				return;
			} finally {
				//退出程序
				System.exit( 0 );
			}
		} else {
			System.exit( 0 );
		}
	}
	/**
	 * 2018年5月12日 下午8:09:11
	 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
	 *	内部类，用来接收 别的用户发送过来的信息
	 */
	class Receive implements Runnable {

		@Override
		public void run() {
			//如果 socket 就退出
			if ( socket == null ) {
				return;
			}
			BufferedReader br = null;
			try {
				br = new BufferedReader( 
						 new InputStreamReader( socket.getInputStream() ) );
				//只有有消息就接收
				while ( true ) {
					String mes = br.readLine();
					//如果 消息无效 就跳过
					if ( mes == null || mes.trim().length() == 0) {
						continue;
					}
					dealWithMessage( mes );
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				return;
			} 
		}
		/**
		 * 处理服务器发送过来的信息
		 * 信息内容格式 --命令关键字@#发送方@#消息内容
		 * 命令关键字：cmdAdd,cmdRem,msg
		 * 发送方: server/otherUserNaem
		 * 消息内容:userNaem/消息内容
		 * @param mes 服务器发送过来的信息
		 */
		private void dealWithMessage(String mes) {
			String[] strs = mes.split( "@#" );
			if ( strs.length < 3 ) {
				return;
			}
			if ( "cmdAdd".equals( strs[0] ) ) { //处理cmdAdd指令
				//更新  allMsg 
				allMsg.append( "通知：用户[" + strs[2] + "]上线了\r\n" );
				//更新 lm 
				lm.addElement( strs[2] ); //更新数据
				list.validate(); //实时更新表现层
			} else if ( "cmdRem".equals( strs[0] ) ) { //处理cmdRem指令
				//更新 allMsg
				allMsg.append( "通知：用户[" + strs[2] + "]下线了\r\n" );
				//更新 lm
				lm.removeElement( strs[2] ); //更新数据
				list.validate(); //实时更新表现层
			} else if ( "msg".equals( strs[0] ) ) { //处理msg指令
				if ( "server".equals( strs[1] ) ) { //如果是服务器发送的信息说明是用来初始化的在线用户列表的信息
					allMsg.append( "欢迎您登入！\r\n" );
					for (int i = 2; i < strs.length; i++) {
						//更新在线用户列表
						lm.addElement( strs[i] ); //更新数据
						list.validate(); //实时更新表现层
					}
				}else {//否则的话，就是用户之间的通信了
					
					allMsg.append( strs[1] + mes.substring( strs[0].length() + strs[1].length() + 4 ) + "\r\n" );
				}
			} 
			
		}
		
	}
	
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new ClientForm();
	}

}
