package cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao;

import cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.a.DAO_A_Factory;

public class DAO_Factory {
	private DAO_Factory(){
	}
	/**
	 * 通过path路径获取到某个实现类
	 * @param path 第0个字符表示数据源如：1表示a机器，2表示b机器，第1个字符表示存储类型，依次类推...
	 * @return 这是一个Api的实现类对象
	 */
	public static Api getInstanceOfApiByPath(String path){
		if("1".equals(path.charAt(0)+"")){//a机器
			return DAO_A_Factory.getInstanceOfApiByPath(path);
		}else if("2".equals(path.charAt(0)+"")){//b机器
			return null;
		}
		return null;
	}
	
}
