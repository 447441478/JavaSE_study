package cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.a.RDB.Oracle.Hibernate;

import cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.Api;

public class HibernateImplApi implements Api {

	@Override
	public void fun(String str) {
		System.out.println("HibernateImplApi:"+str);
	}
	
}
