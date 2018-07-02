package cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.a.RDB;

import cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.Api;
import cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.a.RDB.Oracle.DAO_A_RDB_Oracle_Factory;

public class DAO_A_RDB_Factory {
	private DAO_A_RDB_Factory() {
	}
	
	/**
	 * 通过path路径获取到某个实现类
	 * @param path 第0个字符表示数据源如：1表示a机器，2表示b机器，第1个字符表示存储类型，依次类推
	 * @return 这是一个Api的实现类对象
	 */
	public static Api getInstanceOfApiByPath(String path){
		if("1".equals(path.charAt(2)+"")){//SqlServer
			return null;
		}else if("2".equals(path.charAt(2)+"")){//Oracle
			return DAO_A_RDB_Oracle_Factory.getInstanceOfApiByPath(path);
		}else if("3".equals(path.charAt(2)+"")){//MySQL 
			return null;
		}
		return null;
	}
}
