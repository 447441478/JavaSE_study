package cn.hncu.javaSE.design_设计模式.factory_工厂模式.module2;

import cn.hncu.javaSE.design_设计模式.factory_工厂模式.module1.Api;
import cn.hncu.javaSE.design_设计模式.factory_工厂模式.module1.Module1Factory;

public class Client {
	public static void main(String[] args) {
		Api api = Module1Factory.getInstancOfApi();
		api.fun();
	}
}
