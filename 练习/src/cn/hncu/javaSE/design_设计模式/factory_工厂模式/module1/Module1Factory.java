package cn.hncu.javaSE.design_设计模式.factory_工厂模式.module1;

public class Module1Factory {
	//封掉构造函数
	private Module1Factory(){
	}
	//通过工厂方法获取Api接口的实现类  对象实例
	public static Api getInstancOfApi(){
		return new ImplApi_2();
	}
}
