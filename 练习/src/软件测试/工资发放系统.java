package 软件测试;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.ldap.Rdn;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class 工资发放系统 {

	private JFrame frame;
	private JTextField tfdId;
	private JTextField tfdName;
	private JTextField tfdSalary;
	private JTextField tfdRatio;
	private JTextField tfdDeductMoney;
	private JTextField tfdActualSalary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					工资发放系统 window = new 工资发放系统();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public 工资发放系统() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(450, 100, 500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("工资发放系统");
		label.setForeground(Color.RED);
		label.setFont(new Font("微软雅黑", Font.BOLD, 24));
		label.setBounds(165, 10, 160, 52);
		frame.getContentPane().add(label);
		
		final JRadioButton rbtnYearSalary = new JRadioButton("年薪制NX");
		rbtnYearSalary.setSelected(true);
		rbtnYearSalary.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		rbtnYearSalary.setBounds(191, 218, 97, 23);
		frame.getContentPane().add(rbtnYearSalary);
		
		final JRadioButton rbtnMonthSalary = new JRadioButton("月薪制YX");
		rbtnMonthSalary.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		rbtnMonthSalary.setBounds(290, 218, 104, 23);
		frame.getContentPane().add(rbtnMonthSalary);
		//两个按钮组成一组  形成互斥
		final ButtonGroup btnGroupSalaryType = new ButtonGroup();
		btnGroupSalaryType.add(rbtnYearSalary);
		btnGroupSalaryType.add(rbtnMonthSalary);
		
		JLabel lblNewLabel = new JLabel("员工编号");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(103, 82, 87, 23);
		frame.getContentPane().add(lblNewLabel);
		
		tfdId = new JTextField();
		tfdId.setBounds(191, 82, 197, 23);
		frame.getContentPane().add(tfdId);
		tfdId.setColumns(10);
		
		tfdName = new JTextField();
		tfdName.setColumns(10);
		tfdName.setBounds(191, 130, 197, 23);
		frame.getContentPane().add(tfdName);
		
		JLabel label_1 = new JLabel("姓      名");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_1.setBounds(103, 130, 87, 23);
		frame.getContentPane().add(label_1);
		
		tfdSalary = new JTextField();
		tfdSalary.setColumns(10);
		tfdSalary.setBounds(191, 174, 197, 23);
		frame.getContentPane().add(tfdSalary);
		
		JLabel label_2 = new JLabel("本月工资");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_2.setBounds(103, 174, 87, 23);
		frame.getContentPane().add(label_2);
		
		tfdRatio = new JTextField();
		tfdRatio.setColumns(10);
		tfdRatio.setBounds(191, 295, 197, 23);
		frame.getContentPane().add(tfdRatio);
		
		JLabel label_3 = new JLabel("扣款比例");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_3.setBounds(103, 295, 87, 23);
		frame.getContentPane().add(label_3);
		
		tfdDeductMoney = new JTextField();
		tfdDeductMoney.setColumns(10);
		tfdDeductMoney.setBounds(191, 341, 197, 23);
		frame.getContentPane().add(tfdDeductMoney);
		
		JLabel label_4 = new JLabel("扣款金额");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_4.setBounds(103, 341, 87, 23);
		frame.getContentPane().add(label_4);
		
		tfdActualSalary = new JTextField();
		tfdActualSalary.setColumns(10);
		tfdActualSalary.setBounds(191, 391, 197, 23);
		frame.getContentPane().add(tfdActualSalary);
		
		JLabel label_5 = new JLabel("实发工资");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_5.setBounds(103, 391, 87, 23);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("工资薪制");
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_6.setBounds(103, 218, 87, 23);
		frame.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("错误程度");
		label_7.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_7.setBounds(103, 251, 87, 23);
		frame.getContentPane().add(label_7);
		
		final JCheckBox cbxPT = new JCheckBox("普通PT");
		cbxPT.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		cbxPT.setBounds(191, 251, 103, 23);
		frame.getContentPane().add(cbxPT);
		
		final JCheckBox cbxYZ = new JCheckBox("严重YZ");
		cbxYZ.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		cbxYZ.setBounds(290, 251, 103, 23);
		frame.getContentPane().add(cbxYZ);
		
		JButton btnOk = new JButton("确定");
		btnOk.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//收集参数
				String employeeSalary = tfdSalary.getText().trim();
				double salary = 0;
				try {
					salary = Double.parseDouble(employeeSalary);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "本月工资输入错误，请从新输入！");
					return ;
				}
				int basic = 0;//基础扣款比例
				if (rbtnYearSalary.isSelected()) {
					basic = 2;
				}else{
					basic = 4;
				}
				double ratio = 0;
				if (cbxPT.isSelected()) {
					ratio += basic;
				}
				if (cbxYZ.isSelected()) {
					ratio += 2*basic;
				}
				tfdRatio.setText(ratio+"%");	
				tfdDeductMoney.setText(""+salary*ratio/100);
				tfdActualSalary.setText(""+salary*(100-ratio)/100);
			}
		});
		btnOk.setBounds(145, 452, 87, 35);
		frame.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("取消");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfdActualSalary.setText("");
				tfdDeductMoney.setText("");
				tfdId.setText("");
				tfdName.setText("");
				tfdRatio.setText("");
				tfdSalary.setText("");
				btnGroupSalaryType.clearSelection();
				cbxPT.setSelected(false);
				cbxYZ.setSelected(false);
			}
		});
		btnCancel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnCancel.setBounds(272, 452, 87, 35);
		frame.getContentPane().add(btnCancel);
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
	}
}
