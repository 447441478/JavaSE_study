package cn.hncu.javaSE.classLoader.v1;

import org.junit.Test;

public class ClassLoaderDemo {
	
	@Test//认识三个系统类加载器
	public void t1() {
		
		/*
		 * 每个类加载器加载类时，又先委托给其上级类加载器。
		 * 当所有祖宗类加载器没有加载到类，回到发起者类加载器，
		 * 还加载不了，则抛ClassNotFoundException。
		 * 	BootStrap 是 ExtClassLoader 父类(上级类)
		 * 	ExtClassLoader 是 AppClassLoader 的父类(上级类)
		 */
		
		//AppClassLoader 应用程序类加载器  加载 classpath指定的所有jar或目录
		ClassLoader classLoader = Person.class.getClassLoader();
		System.out.println( "111>>>" + classLoader.toString() );
		
		//ExtClassLoader 扩展类加载器 加载JRE/lib/ext/*.jar
		classLoader = classLoader.getParent();
		System.out.println( "222>>>" + classLoader.toString() );
		
		//BootStrap 不是java类 所以为 null 加载 JRE/lib/rt.jar 
		classLoader = classLoader.getParent();
		System.out.println( "333>>>" + classLoader );
		
		//我们 定义的 类 默认都是 通过 AppClassLoader 加载的
		//接下来 看看 String 的加载类是谁
		ClassLoader classLoader2 = String.class.getClassLoader();
		System.out.println( classLoader2 ); //null
		// null -> BootStrap 最原始的 类加载器。 
		// 通过观察 可以发现 String 是处于 JRE/lib/rt.jar 中的 
		// 所以，验证了 父类委托机制   BootStrap加载了 其子类或者孙类 就不加载了
	}
	
	/*
	 演示父类委托机制: 如果把Person.class打包成a.jar,
	 并把它放在"JRE/lib/ext/"目录下，则无论在当前项目中如何修改person类，运行时都不会改变。
	 */
	@Test
	public void t2() {
		Person p = new Person( "Tom",18 );
		System.out.println( p );
	}
}
