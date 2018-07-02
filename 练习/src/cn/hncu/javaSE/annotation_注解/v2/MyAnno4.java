package cn.hncu.javaSE.annotation_注解.v2;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 2018年5月20日 上午9:46:41
 * @author <a href="mailto:447441478@qq.com">宋进宇</a><br/>
 *	注解的注解
 */

@Inherited //在默认的情况下，父类的注解并不会被子类继承。如果要继承，就必须加上Inherited注解。 
@Documented //在默认的情况下在使用javadoc自动生成文档时，注解将被忽略掉。如果想在文档中也包含注解，必须使用Documented为文档注解。
//Retention 属性 只能一个 注意：注解想要在程序中起作用 必须 @Retention( RetentionPolicy.RUNTIME ) 才可以
@Retention( RetentionPolicy.RUNTIME ) //编译器将把注释记录在类文件中，在运行时 VM 将保留注释，因此可以反射性地读取。
//@Retention( RetentionPolicy.CLASS ) //编译器将把注释记录在类文件中，但在运行时 VM 不需要保留注释。也就是说，编译后的class文件中保留，但是执行时是不起作用的
//@Retention( RetentionPolicy.SOURCE ) //编译器要丢弃的注释，也就是说只有在程序员编程过程器作用 如@Override

//Target 属性是数组
@Target( { ElementType.METHOD, ElementType.CONSTRUCTOR } ) //即可在方法上，也可以在构造方法上，依次类推
//@Target( { ElementType.METHOD } ) //只能在方法上
//@Target( { ElementType.CONSTRUCTOR } ) //只能在构造方法上
//@Target( { ElementType.FIELD } ) //只能在属性上
public @interface MyAnno4 {
	
	String schoolName() default "湖南城市学院";
}
