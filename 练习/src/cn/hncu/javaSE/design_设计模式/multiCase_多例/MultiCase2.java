package cn.hncu.javaSE.design_设计模式.multiCase_多例;

import java.util.HashMap;
import java.util.Map;
/**
 * Time:2018/4/14
 * Description：多例---不限个数
 * @author 宋进宇
 */
public class MultiCase2 {
	//一加载类模板时，就创建一个map容器
	private static Map<Integer, MultiCase2> multiCase2s = new HashMap<Integer, MultiCase2>();
	//设置该容器中最多的实例个数
	private final static int MAX = 3;
	private static int num = 0;//已使用个数
	//封掉构造函数
	private MultiCase2(){
	}
	//对外提供一个可以获取一个对象实例的方法
	public static MultiCase2 getInstance(){
		MultiCase2 value = multiCase2s.get(num);
		if (value==null) {
			value = new MultiCase2();
			multiCase2s.put(num, value);
		}
		num++;
		if (num>MAX) {
			//根据项目来控制，本例直接清0
			num = 0;
		}
		return value;
	}
}
