package cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.a.RDB.Oracle;

import cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.Api;
import cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.a.RDB.Oracle.EJB.EJB_ImplApi;
import cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.a.RDB.Oracle.Hibernate.HibernateImplApi;
import cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.a.RDB.Oracle.JDBC.JDBC_ImplApi;
import cn.hncu.javaSE.design_设计模式.dao_工厂接力.dao.a.RDB.Oracle.JPA.JPA_ImplApi;

public class DAO_A_RDB_Oracle_Factory {
	private DAO_A_RDB_Oracle_Factory() {
	}
	
	/**
	 * 通过path路径获取到某个实现类
	 * @param path 第0个字符表示数据源如：1表示a机器，2表示b机器，第1个字符表示存储类型，依次类推
	 * @return 这是一个Api的实现类对象
	 */
	public static Api getInstanceOfApiByPath(String path){
		if("1".equals(path.charAt(3)+"")){//JDBC
			return new JDBC_ImplApi();
		}else if("2".equals(path.charAt(3)+"")){//Hibernate
			return new HibernateImplApi();
		}else if("3".equals(path.charAt(3)+"")){//EJB
			return new EJB_ImplApi();
		}else if("4".equals(path.charAt(3)+"")){//JPA
			return new JPA_ImplApi();
		}
		return null;
	}
}
