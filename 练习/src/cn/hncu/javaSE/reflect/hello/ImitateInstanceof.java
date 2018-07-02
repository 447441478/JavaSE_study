package cn.hncu.javaSE.reflect.hello;

/**
 * 2018年5月17日 下午4:07:07
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	通过类反射模仿 instanceof
 */
public class ImitateInstanceof {
	
	public static void main(String[] args) {
		Class<String> c = String.class;
		String str = new String();
		System.out.println( c.isInstance( str ) ); //true
		Integer i = new Integer(1);
		System.out.println( c.isInstance( i ) ); //false
	}
}
