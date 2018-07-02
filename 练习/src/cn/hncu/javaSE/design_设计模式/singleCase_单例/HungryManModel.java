package cn.hncu.javaSE.design_设计模式.singleCase_单例;
/**
 * Time：2018/4/13
 * Description：单例---饿汉模式
 * @author 宋进宇
 */
public class HungryManModel {
	//加载类模板时就加载实例。
	private static final HungryManModel HUNGRY_MAN_MODEL = new HungryManModel();
	//私有化构造方法，以便实现单例
	private HungryManModel(){
	}
	//获取实例
	public static HungryManModel getInstance(){
		return HUNGRY_MAN_MODEL;
	}
}
