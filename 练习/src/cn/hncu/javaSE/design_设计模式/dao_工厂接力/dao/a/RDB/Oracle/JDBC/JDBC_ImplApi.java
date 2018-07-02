package cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.a.RDB.Oracle.JDBC;

import cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.Api;

public class JDBC_ImplApi implements Api{
	@Override
	public void fun(String str) {
		System.out.println("JDBC_ImplApi:"+str);
	}
}
