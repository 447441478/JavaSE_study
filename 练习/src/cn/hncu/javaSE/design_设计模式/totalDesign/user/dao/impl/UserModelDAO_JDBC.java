package cn.hncu.javaSE.design_设计模式.totalDesign.user.dao.impl;

import java.util.Collection;

import cn.hncu.javaSE.design_设计模式.totalDesign.user.dao.dao.UserModelDAO;
import cn.hncu.javaSE.design_设计模式.totalDesign.user.vo.UserModel;
import cn.hncu.javaSE.design_设计模式.totalDesign.user.vo.UserQueryModel;

public class UserModelDAO_JDBC implements UserModelDAO {

	@Override
	public boolean add(UserModel user) {
		System.out.println("JDBC:"+user);
		return true;
	}

	@Override
	public boolean delete(UserModel user) {
		return false;
	}

	@Override
	public boolean update(UserModel user) {
		return false;
	}

	@Override
	public UserModel getUserModelById(String id) {
		return null;
	}

	@Override
	public Collection<UserModel> getAll() {
		return null;
	}

	@Override
	public Collection<UserModel> getUserModelsByCondition(UserQueryModel uqm) {
		return null;
	}

}
