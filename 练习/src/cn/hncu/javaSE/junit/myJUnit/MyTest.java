package cn.hncu.javaSE.junit.myJUnit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention( RetentionPolicy.RUNTIME ) //必须要有运行时是不起作用的
@Target( { ElementType.METHOD } ) //限定只能在方法上使用
public @interface MyTest {
}
