package cn.hncu.javaSE.reflect.hello.usb.impl;

import cn.hncu.javaSE.reflect.hello.usb.USB;

/**
 * 2018年5月17日 下午3:30:21
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	USB接口的鼠标
 */
public class USBMouse implements USB {

	@Override
	public void work() {
		System.out.println( "USBMouse,working..." );
	}
	
}
