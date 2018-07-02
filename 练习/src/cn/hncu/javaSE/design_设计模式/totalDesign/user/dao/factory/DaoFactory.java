package cn.hncu.javaSE.design_设计模式.totalDesign.user.dao.factory;

import cn.hncu.javaSE.design_设计模式.totalDesign.user.dao.dao.UserModelDAO;
import cn.hncu.javaSE.design_设计模式.totalDesign.user.dao.impl.UserModelDAO_JDBC;

public class DaoFactory {
	private DaoFactory(){
	}
	
	public static UserModelDAO getInstanceOfUserModelDAO(){
		return new UserModelDAO_JDBC();
	}
}
