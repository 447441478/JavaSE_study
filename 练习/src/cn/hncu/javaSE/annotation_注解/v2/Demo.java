package cn.hncu.javaSE.annotation_注解.v2;

import java.lang.reflect.Method;

import cn.hncu.javaSE.annotation_注解.v1.MyAnno3;

/**
 * 2018年5月20日 上午10:02:17
 * @author <a href="mailto:447441478@qq.com">宋进宇</a><br/>
 *	演示 注解的注解
 *	在读取注解的时 ，被读取的注解必须被 @Retention( RetentionPolicy.RUNTIME )注解
 */
//这里是无法使用注解    @MyAnno4  因为该注解 被 注解为只能使用在方法或构造方法上
@MyAnno3
public class Demo {
	
	@MyAnno4
	@MyAnno3
	public static void aa(){
		System.out.println( "aaaaaaaaa" );
	}
	
	//演示 读取注解
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		//获取当前类的 类模板对象
		Class<Demo> c = Demo.class;
		MyAnno3 myAnno3 = c.getAnnotation( MyAnno3.class );
		System.out.println( "MyAnno3::" + myAnno3 );
		//额 怎么明明 当前类 有 MyAnno3 注解 可是为什么 打印出来null？
		/* 答案： @MyAnno3 默认是被@Retention( RetentionPolicy.CLASS )
		 *		只在class文件中保留，但是执行时是不起作用的，即 null
		 */
		//接下来 看看 被@Retention( RetentionPolicy.RUNTIME )注解的 @MyAnno4
		//1.先获取 该类模板上的 Method
		Method method = c.getDeclaredMethod( "aa", new Class[0] );
		myAnno3 = method.getAnnotation( MyAnno3.class );
		MyAnno4 myAnno4 = method.getAnnotation( MyAnno4.class );
		System.out.println( "MyAnno3::" + myAnno3 ); //这里还是 null 原理同上
		System.out.println( "MyAnno4>>" + myAnno4 ); //这里就不为 null 了。
		//接下来读取 myAnno4 中的属性值
		String schoolName = myAnno4.schoolName();
		System.out.println( "schoolName>>" + schoolName ); //可以的！！
		
	}
}
