package cn.hncu.javaSE.design_设计模式.singleCase_单例;
/**
 * Time：2018/4/13
 * Description：单例---懒汉模式
 * @author 宋进宇
 */
public class LazyManModel {
	//加载类模板时不加载实例，到调用获取实例方法时，在实例化对象
	private static LazyManModel lazyManModel = null;
	//私有化构造函数，以便实现单例
	private LazyManModel(){
	}
	//提供一个获取实例的方法
	//★★★在多线程的情况中需要用 synchronized 关键字 对该方法加锁。
	public synchronized static LazyManModel getInstance() {
		//如果是第一次调用获取实例方法，则创建一个对象，否则直接返回lazyManModel
		if (lazyManModel==null) {
			lazyManModel = new LazyManModel();
		}
		return lazyManModel;
	}
}
