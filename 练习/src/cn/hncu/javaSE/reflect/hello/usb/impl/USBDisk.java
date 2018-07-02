package cn.hncu.javaSE.reflect.hello.usb.impl;

import cn.hncu.javaSE.reflect.hello.usb.USB;

/**
 * 2018年5月17日 下午3:43:50
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	U盘
 */
public class USBDisk implements USB {

	@Override
	public void work() {
		System.out.println( "USBDisk,working..." );
	}
	
}
