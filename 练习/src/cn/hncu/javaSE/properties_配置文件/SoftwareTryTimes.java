package cn.hncu.javaSE.properties_配置文件;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class SoftwareTryTimes {
	public static void main(String[] args) {
		if(validSoftware()){
			System.out.println("正常运行...");
		}else{
			JOptionPane.showMessageDialog(null, "软件试用次数已到,请注册");
		}
	}

	private static boolean validSoftware() {
		Properties p = new Properties();
		//记录试用次数的配置文件
		File file = new File("config.cfg");
		//第一次试用该软件时应该创建配置文件
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "软件已损坏");
				System.exit(0);
			}
		}
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			//读取配置文件
			fis = new FileInputStream(file);
			p.load(fis);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "软件已损坏");
			System.exit(0);
		}
		//第一次试用该软件时，默认可以使用5次
		String value = p.getProperty("count", "5");
		int time = 0;
		try {
			time = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "软件已损坏");
			System.exit(0);
		}
		time--;
		if (time <= 0) {
			return false;
		}else{
			try {
				//更新配置文件数据，并保存
				p.setProperty("count", time+"");
				fos = new FileOutputStream(file);
				p.store(fos,"");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "软件已损坏");
				System.exit(0);
			}
			if (fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "软件已损坏");
					System.exit(0);
				}
			}
			if (fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "软件已损坏");
					System.exit(0);
				}
			}
			return true;
		}
	}
}
