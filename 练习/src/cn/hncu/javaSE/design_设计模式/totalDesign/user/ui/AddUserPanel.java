package cn.hncu.javaSE.design_设计模式.totalDesign.user.ui;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import cn.hncu.javaSE.design_设计模式.totalDesign.user.business.ebi.UserModelEbi;
import cn.hncu.javaSE.design_设计模式.totalDesign.user.business.factory.BusinessFactory;
import cn.hncu.javaSE.design_设计模式.totalDesign.user.vo.UserModel;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.util.UUID;
import java.awt.event.ActionEvent;

public class AddUserPanel extends JPanel {
	private JTextField tfdName;
	private JTextField tfdAge;
	private JFrame mainFrame;
	//注入ebi
	private UserModelEbi ebi = BusinessFactory.getInstanceOfUserModelEbi();
	/**
	 * Create the panel.
	 * @param mainFrame 
	 */
	public AddUserPanel(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		setBackground(new Color(173, 216, 230));
		setMinimumSize(new Dimension(800, 600));
		setLayout(null);
		
		JLabel label = new JLabel("添加用户界面");
		label.setForeground(new Color(255, 0, 0));
		label.setFont(new Font("微软雅黑", Font.BOLD, 36));
		label.setBounds(261, 41, 236, 67);
		add(label);
		
		JLabel label_1 = new JLabel("姓名：");
		label_1.setForeground(new Color(0, 0, 255));
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 24));
		label_1.setBounds(215, 145, 79, 33);
		add(label_1);
		
		JLabel label_2 = new JLabel("年龄：");
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 24));
		label_2.setBounds(215, 223, 79, 33);
		add(label_2);
		
		tfdName = new JTextField();
		tfdName.setBounds(300, 145, 197, 32);
		add(tfdName);
		tfdName.setColumns(10);
		
		tfdAge = new JTextField();
		tfdAge.setColumns(10);
		tfdAge.setBounds(300, 224, 197, 32);
		add(tfdAge);
		
		JButton btnAdd = new JButton("添加");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddActionPerformed(e);
			}
		});
		btnAdd.setForeground(new Color(0, 0, 255));
		btnAdd.setFont(new Font("微软雅黑", Font.BOLD, 24));
		btnAdd.setBounds(201, 337, 93, 41);
		add(btnAdd);
		
		JButton btnBack = new JButton("返回");
		btnBack.setForeground(Color.BLUE);
		btnBack.setFont(new Font("微软雅黑", Font.BOLD, 24));
		btnBack.setBounds(385, 337, 93, 41);
		add(btnBack);
	}
	
	private void btnAddActionPerformed(ActionEvent e) {
		//1 收集参数 id name age
		String name = tfdName.getText();
		String strAge = tfdAge.getText();
		//2 组织参数
		Integer age = null;
		if (strAge!=null &&strAge.trim().length()>0) {
			age = Integer.parseInt(strAge);
		}
		UserModel user = new UserModel();
		user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		user.setName(name);
		user.setAge(age);
		//3 调用逻辑层
		boolean boo = ebi.add(user);
		//4 导向结果界面
		if (boo) {
			JOptionPane.showMessageDialog(null, "添加成功！！！");
		}
	}
}
