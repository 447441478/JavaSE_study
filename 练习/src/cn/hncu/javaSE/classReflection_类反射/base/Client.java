package cn.hncu.javaSE.classReflection_类反射.base;

import java.io.FileInputStream;
import java.util.Properties;

public class Client {
	public static void main(String[] args) throws Exception {
		Api api = new ImplApi_1();
		api.fun();
		api = new ImplApi_2();
		api.fun();
		//类反射方法+配置文件
		Properties p = new Properties();
		//配置文件在当前类的同一层次，文件查找都是从项目根目录开始找
		p.load(new FileInputStream("src/cn/hncu/obj/classReflection_类反射/base/api.properties"));
		String className = p.getProperty("className");
		Class c = Class.forName(className);
		api = (Api) c .newInstance();
		api.fun();
	}
}
