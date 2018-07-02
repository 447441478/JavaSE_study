package cn.hncu.javaSE.reflect.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {
	
	/**
	 * 给定 一个类模板对象 和 对应的 属性的map 就可以封装出一个 该类模板对象的一个实例
	 * @param c 类模板对象
	 * @param map 对应的属性的map
	 * @return 该类模板对象的一个实例
	 * @throws NoSuchMethodException
	 * @throws SecurityException 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static<T> T populate( Class<T> c, Map<String, Object> map ) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		//先构造一个空参对象
		//1.获得空参构造器
		Constructor<T> con = c.getDeclaredConstructor( new Class[0] );
		//2.获得一个实例对象
		T obj = con.newInstance( new Object[0] );
		//获取c中所有属性
		Field[] flds = c.getDeclaredFields();
		//如果没有属性直接返回 obj
		if ( flds == null || flds.length == 0 ) {
			return obj;
		}
		//遍历所有属性
		for (Field field : flds) {
			//获取属性名称
			String fieldName = field.getName();
			//到map找 是否有对应的 值
			Object value = map.get( fieldName );
			//如果 value 为null 则跳过
			if ( value == null ) {
				continue;
			}
			//生成 符合规则的 方法名 set+属性名首字符大写+剩余部分
			String methodName = "set" + fieldName.substring( 0, 1 ).toUpperCase() + fieldName.substring( 1 );
			//获取方法
			Method method = c.getDeclaredMethod( methodName, field.getType() );
			//调用方法
			method.invoke( obj, value );
			
		}
		
		return obj;
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "name", "abc" );
		map.put( "age", 22 );
		map.put( "price", 12.3 );
		Person p = populate( Person.class, map ); //任意符合JavaBean规范的值对象类
		System.out.println( p );
		Book b = populate( Book.class, map ); //任意符合JavaBean规范的值对象类
		System.out.println( b );
	}
}
