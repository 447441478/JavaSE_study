package cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.a.RDB.Oracle.EJB;

import cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.Api;

public class EJB_ImplApi implements Api {

	@Override
	public void fun(String str) {
		System.out.println("EJB_ImplApi:"+str);
	}
	
}
