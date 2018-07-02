package cn.hncu.javaSE.design_设计模式.totalDesign.user.business.factory;

import cn.hncu.javaSE.design_设计模式.totalDesign.user.business.ebi.UserModelEbi;
import cn.hncu.javaSE.design_设计模式.totalDesign.user.business.ebo.UserModelEbo;

public class BusinessFactory {
	private BusinessFactory(){
	}
	
	public static UserModelEbi getInstanceOfUserModelEbi(){
		return new UserModelEbo();
	}
}
