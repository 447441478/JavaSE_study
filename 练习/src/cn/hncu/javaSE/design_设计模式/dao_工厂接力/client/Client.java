package cn.hncu.javaSE.design_设计模式.dao_工厂接力.client;

import cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.Api;
import cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.DAO_Factory;

public class Client {
	public static void main(String[] args) {
		Api api = DAO_Factory.getInstanceOfApiByPath("1221");
		api.fun("Hello World!");
		api = DAO_Factory.getInstanceOfApiByPath("1222");
		api.fun("Hello World!");
		api = DAO_Factory.getInstanceOfApiByPath("1223");
		api.fun("Hello World!");
		api = DAO_Factory.getInstanceOfApiByPath("1224");
		api.fun("Hello World!");
	}
}
