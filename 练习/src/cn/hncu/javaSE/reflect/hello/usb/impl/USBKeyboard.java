package cn.hncu.javaSE.reflect.hello.usb.impl;

import cn.hncu.javaSE.reflect.hello.usb.USB;

/**
 * 2018年5月17日 下午3:31:49
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	USB接口的键盘
 */
public class USBKeyboard implements USB {

	@Override
	public void work() {
		System.out.println( "USBKeyboard,working..." );
	}
	
}
