package cn.hncu.javaSE.design_设计模式.multiCase_多例;

import java.util.HashMap;
import java.util.Map;
/**
 * Time:2018/4/14
 * Description：多例---不限个数
 * @author 宋进宇
 */
public class MultiCase {
	//一加载类模板时，就创建一个map容器
	private static Map<String, MultiCase> multiCases = new HashMap<String, MultiCase>();
	//看项目需求，是否需要封掉构造函数，本例采用封掉构造函数
	private MultiCase(){
	}
	//对外提供一个可以通过key来获取一个对象实例
	public static MultiCase getInstanceByKey(String key) {
		//先通过key从容器中获取一个值
		MultiCase value = multiCases.get(key);
		//如果是null说明该对象还没被实例化，即第一次通过这个key值来获取实例
		if (value==null) {
			synchronized (multiCases) {//****第一次通过这个key值来获取实例时，把池加锁
				value = multiCases.get(key);//****通过key值从新获取value，这一步很关键，防止两个线程通过相同key，获取value都是null。
				if (value==null) {//****如果还是null说明这是真正的第一次。
					value = new MultiCase();
					multiCases.put(key, value);
				}
			}
		}
		return value;
	}

}
