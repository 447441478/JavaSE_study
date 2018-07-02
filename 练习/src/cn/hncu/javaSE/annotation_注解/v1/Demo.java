package cn.hncu.javaSE.annotation_注解.v1;

@MyAnno3( schoolName = "666666" ) //赋值
@MyAnno1
public class Demo {
	
	@MyAnno3 //不赋值
	@MyAnno1
	private int i;
	
	@MyAnno2( schoolName = "湖南城市学院" ) //必须赋值
	private double d;
	
	@MyAnno2( schoolName = "hncu" ) //必须赋值
	@MyAnno1
	public static void aa() {
		System.out.println( "aaaaaaaaaaa" );
	}
}
