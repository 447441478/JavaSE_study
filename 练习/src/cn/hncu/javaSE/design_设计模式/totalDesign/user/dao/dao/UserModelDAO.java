package cn.hncu.javaSE.design_设计模式.totalDesign.user.dao.dao;

import java.util.Collection;

import cn.hncu.javaSE.design_设计模式.totalDesign.user.vo.UserModel;
import cn.hncu.javaSE.design_设计模式.totalDesign.user.vo.UserQueryModel;
//DAO层通用模板：增、删、改、查(单、全、条件)
public interface UserModelDAO{
	public boolean add(UserModel user);
	
	public boolean delete(UserModel user);
	
	public boolean update(UserModel user);
	
	public UserModel getUserModelById(String id);
	
	public Collection<UserModel> getAll();
	
	public Collection<UserModel> getUserModelsByCondition(UserQueryModel uqm);
}