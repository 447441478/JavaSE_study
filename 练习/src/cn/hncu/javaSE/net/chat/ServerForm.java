package cn.hncu.javaSE.net.chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

/**
 * 2018年5月13日 上午7:39:54
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *
 */
public class ServerForm extends JFrame {
	private static final long serialVersionUID = 1L;

	//服务器的端口
	private static int PORT = 9999;
	
	private JList<String> list; //在线用户列表--表现层
	private DefaultListModel<String> lm; //在线用户列表--数据层
	
	private JTextArea allInfo = new JTextArea(); //信息显示主窗口
	
	private Map<String, Socket> userSockets = new HashMap<String, Socket>();

	private JMenuItem itemRun;
	
	public ServerForm() {
		setTitle( "啊哈哈-服务器" );
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//1东 在线用户列表
		lm = new DefaultListModel<String>();
		lm.addElement( "全部" );
		list = new JList<String>( lm );
		list.setVisibleRowCount( 5 );
		JScrollPane jsc = new JScrollPane( list );
		jsc.setBorder( new TitledBorder( "在线" ) );
		jsc.setPreferredSize( new Dimension( 100, this.getHeight() ) );
		getContentPane().add( jsc, BorderLayout.EAST );
		
		//2中  信息主窗口 
		allInfo.setEditable( false );
		getContentPane().add( new JScrollPane( allInfo ) );
		
		//菜单
		JMenuBar menubar = new JMenuBar();
		setJMenuBar( menubar );
		JMenu menu = new JMenu( "控制(C)" );
		menubar.add( menu );
		menu.setMnemonic( 'C' ); //设置菜单助记符
		itemRun = new JMenuItem( "开启" );
		itemRun.setActionCommand( "run" );
		itemRun.setAccelerator(KeyStroke.getKeyStroke( 'R', KeyEvent.CTRL_MASK ) );//设置快捷键: Ctrl+R
		menu.add( itemRun );
		menu.addSeparator();
		JMenuItem itemExit = new JMenuItem( "退出" );
		itemExit.setActionCommand( "exit" );
		itemExit.setAccelerator( KeyStroke.getKeyStroke( 'E', KeyEvent.CTRL_MASK ) );//设置快捷键: Ctrl+E
		menu.add( itemExit );
		
		//监听器
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if( e.getActionCommand().equals( "run" ) ){
					new Thread() {//采用 匿名内部类，重写Thread的 run方法
						public void run() {
							serverRun();
						}
					}.start();
					
				}else if( e.getActionCommand().equals( "exit" ) ){
					System.exit( 0 );
				}
				
			}
		};
		//给2个菜单项添加监听
		itemRun.addActionListener( al );
		itemExit.addActionListener( al );
		
		
		
		int winWidth = 500;
		int winHeight = 400;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int width = (int) toolkit.getScreenSize().getWidth();
		int height =  (int)toolkit.getScreenSize().getHeight();
		setBounds( width/2 - winWidth/2, height/2 - winHeight/2, winWidth, winHeight );
		
		setVisible(true);
	}
	/**
	 * 启动服务器
	 */
	protected void serverRun() {
		ServerSocket server = null;
		try {
			server = new ServerSocket( PORT );
			//在 服务器信息栏 添加服务器启动信息
			allInfo.append( server + "--服务器启动了\r\n" );
			//启动服务器后，关闭启动菜单项
			itemRun.setEnabled( false );
			while ( true ) {
				Socket s = server.accept();
				new Thread( new UserRun( s ) ).start();
			}
		} catch (IOException e) {
			System.exit( -1 );
		} finally {
			if ( server != null ) {
				try {
					server.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	class UserRun implements Runnable {
		private Socket s = null;

		public UserRun(Socket s) {
			this.s = s;
		}

		@Override
		public void run() {
			//用户一连接上就获取用户IP
			String userIp = s.getInetAddress().getHostAddress();
			BufferedReader br = null;
			try {
				//能到这里说明一个用户链接成功
				//获取用户名
				//这里规定用户名为一行
				br = new BufferedReader(
						 new InputStreamReader( s.getInputStream() ) );
				
				String userName = br.readLine();
				//把用户信息放人 userSockets 中
				userSockets.put( userName, s );
				//更新allInfo
				allInfo.append( "IP:" + userIp + ",port:" + s.getPort() + ",userName:" + userName + ",上线了！\r\n");
				lm.addElement( userName );
				//把在线的用户信息反馈给当前用户
				initData();
				//同时通知其他用户
				String mes = "cmdAdd@#server@#" + userName;
				notifyOthersUser( mes, userName );
				//通知完毕后等待用户发来信息
				while ( true ) {
					//用户信息
					mes = br.readLine();
					//如果 mes 为无效数据就跳过
					if ( mes == null || mes.trim().length() == 0) {
						continue;
					}
					//解析用户信息
					//用户信息规则：命令关键字@#接收方@#发送方@#消息内容
					//命令：exit/send,接收方：all/otherUserName,发送方：userName,消息内容：...
				
					try {
						dealWithMessage( mes );
					} catch (Exception e) {
						//用户下线就退出循环
						break;
					}
				}
				
			} catch (IOException e) {
			}
		}
		/**
		 * 给用户反馈当前 在线 用户。
		 */
		private void initData() {
			try {
				PrintWriter pw = new PrintWriter( s.getOutputStream(), true );
				StringBuilder sb = new StringBuilder( "msg@#server" );
				Set<String> names = userSockets.keySet();
				for (String name : names) {
					sb.append( "@#" + name );
				}
				pw.println( sb.toString() );
			} catch (IOException e) {
				e.printStackTrace();
				return ;
			}
			
		}

		/**
		 * 处理用户发送的信息，
		 * 用户信息规则：命令关键字@#接收方@#发送方@#消息内容，
		 * 命令：exit/send,
		 * 接收方：all/otherUserName,
		 * 发送方：userName,
		 * 消息内容：...
		 * @param mes 用户发送的信息
		 * @throws Exception 抛出该异常说明用户下线了
		 */
		private void dealWithMessage( String mes ) throws Exception {
			//如果消息无效 就不处理
			if ( mes == null || mes.trim().length() == 0) {
				return;
			}
			String[] strs = mes.split("@#");
			if ( "exit".equals( strs[0] ) ) {//进行退出处理
				String str = "cmdRem@#server@#" + strs[2] ;
				notifyOthersUser( str, strs[2] );
				//同时在 userSockets中移除该用户
				Socket s = userSockets.remove( strs[2] );
				//更新allInfo
				allInfo.append( "IP:" + s.getInetAddress().getHostAddress() + ",port:" + s.getPort() + ",userName:" + strs[2] + ",下线了！\r\n");
				lm.removeElement( strs[2] );
				//关闭s
				s.close();
				throw new Exception();
			} else if ( "send".equals( strs[0] ) ){//进行发送处理
				//组织信息
				String str = "msg@#" + strs[2] + "@#说：" + mes.substring( strs[0].length() + strs[1].length() + strs[2].length() + 6 );
				if ( "all".equals( strs[1] ) ) {//如果是发送给所有人
					System.out.println( str );
					notifyOthersUser( str, strs[2] );
				} else {
					str = "msg@#" + strs[2] + "@#悄悄对您说：" + mes.substring( strs[0].length() + strs[1].length() + strs[2].length() + 6 );
					Socket target = userSockets.get( strs[1] );
					System.out.println( strs[1] );
					PrintWriter pw = new PrintWriter( target.getOutputStream(), true );
					pw.println( str );
				}
			}
		}

		/**
		 * 通知除了  userName 的其他用户。通知内容格式 --命令关键字@#发送方@#消息内容
		 * 命令关键字：cmdAdd,cmdRem,msg
		 * 发送方: server/otherUserNaem
		 * 消息内容:userNaem/消息内容
		 * @param mes 通知内容格式 --命令关键字@#发送方@#消息内容
		 * @param userName 该用户状态
		 */
		private void notifyOthersUser(String mes, String userName) {
			Set<Entry<String, Socket>> entrys = userSockets.entrySet();
			for (Entry<String, Socket> en : entrys) {
				//如果是当前用户就跳过
				if ( en.getKey().equals( userName ) ){
					continue;
				}
				Socket s = en.getValue();
				try {
					PrintWriter out = new PrintWriter( s.getOutputStream(), true );
					//给 除了 userName 的其他所有用户发送消息
					out.println( mes );
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new ServerForm();
	}
	
}
