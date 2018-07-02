package cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.a.RDB.Oracle.JPA;

import cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.Api;

public class JPA_ImplApi implements Api {
	@Override
	public void fun(String str) {
		System.out.println("JPA_ImplApi:"+str);
	}
}
