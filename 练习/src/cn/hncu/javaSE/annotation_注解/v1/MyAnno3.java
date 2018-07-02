package cn.hncu.javaSE.annotation_注解.v1;
/**
 * 2018年5月20日 上午9:41:54
 * @author <a href="mailto:447441478@qq.com">宋进宇</a><br/>
 *	该注解 可以 给属性 赋值 也可以不赋值 
 */
public @interface MyAnno3 {
	
	String schoolName() default "HNCU"; //通过 关键字 default 给 schoolName 赋 初始值
}
