package cn.hncu.javaSE.reflect.hello.usb;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * 2018年5月17日 下午3:45:02
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	模拟用户通过USB接口使用各种USB接口的外设
 */
public class Client {
	
	public static void main(String[] args) {
		USB u = null;
		try {
			u = UsbFactory.getUSB();
			u.work();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e) {
			e.printStackTrace();
		}
	}
}
