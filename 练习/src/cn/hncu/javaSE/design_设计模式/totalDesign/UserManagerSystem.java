package cn.hncu.javaSE.design_设计模式.totalDesign;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JMenuBar;

import cn.hncu.javaSE.design_设计模式.totalDesign.user.ui.AddUserPanel;

import javax.swing.JMenu;
import java.awt.Cursor;

public class UserManagerSystem {

	private JFrame mainFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManagerSystem window = new UserManagerSystem();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserManagerSystem() {
		initialize();
		mainFrame.setContentPane(new AddUserPanel(mainFrame));
		mainFrame.validate();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("用户管理系统");
		mainFrame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		mainFrame.getContentPane().setBackground(new Color(176, 224, 230));
		mainFrame.setMinimumSize(new Dimension(800, 600));
		mainFrame.setBackground(new Color(127, 255, 212));
		mainFrame.setBounds(300, 100, 450, 300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		menuBar.setMaximumSize(new Dimension(0, 8));
		menuBar.setMinimumSize(new Dimension(0, 3));
		mainFrame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
	}

}
