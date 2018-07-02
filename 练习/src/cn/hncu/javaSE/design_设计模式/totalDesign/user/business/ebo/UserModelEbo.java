package cn.hncu.javaSE.design_设计模式.totalDesign.user.business.ebo;

import cn.hncu.javaSE.design_设计模式.totalDesign.user.business.ebi.UserModelEbi;
import cn.hncu.javaSE.design_设计模式.totalDesign.user.dao.dao.UserModelDAO;
import cn.hncu.javaSE.design_设计模式.totalDesign.user.dao.factory.DaoFactory;
import cn.hncu.javaSE.design_设计模式.totalDesign.user.vo.UserModel;

public class UserModelEbo implements UserModelEbi {
	//注入dao
	UserModelDAO dao = DaoFactory.getInstanceOfUserModelDAO();
	
	@Override
	public boolean add(UserModel user) {
		return dao.add(user);
	}
}
