package cn.hncu.javaSE.generic_泛型.v1;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Time：2018/4/5
 * Description：
	  泛型一共就3种:
	  1.定义在“类”上
	  2.定义在“方法”上
	  3.定义在“接口”上
 * @author 宋进宇
 */
public class Demo {
	@Test//1: 定义在“类”上
	public void test1(){
		MySet<String> mySet = new MySet<String>();
		mySet.add("123");
		mySet.add("abc");
		Set<String> set = mySet.getAll();
		set.add("xxx");
		for (String string : set) {
			System.out.println(string);
		}
	}
	@Test//2: 定义在“方法”上
	public void test2(){
		MySet<Integer> mySet = new MySet<Integer>();
		//The method add(Integer) in the type MySet<Integer> is not applicable for the arguments (String)
		//mySet.add("abc");//WA
		mySet.add(123);//2.1 泛型方法的定义(与类的泛型捆绑)
		
		String str = mySet.aa("abc");//2.2泛型方法的定义(独立于类的泛型)
		System.out.println(str);
		
		MySet.bb(3.14159);//2.3 泛型方法的定义(静态方法的泛型)
	}
	@Test//3：定义在接口上的泛型
	public void test3(){
		//3.1 实现类把接口中的泛型写"死" 的用法
		//Type mismatch: cannot convert from A to Interface<Integer>
		//Interface<Integer> a1 = new A();WA
		Interface<String> a2 = new A();
		a2.show("xyz");
		
		//3.2 实现类把接口中的泛型写"活" 的用法
		Interface<Integer> b1 = new B<Integer>();
		b1.show(123);
		Interface<String> b2 = new B<String>();
		b2.show("abc");
	}
}

class MySet<E>{//1: 定义在“类”上
	private Set<E> set = new HashSet<E>();
	public boolean add(E e){//2.1 泛型方法的定义(与类的泛型捆绑)
		return set.add(e);
	}
	public Set<E> getAll(){
		return set;
	}
	
	public <T> T aa(T t){//2.2 泛型方法的定义(独立于类的泛型)
		return t;
	}
	
	public static<T> void bb(T t){//2.3 泛型方法的定义(静态方法的泛型)
		System.out.println(t.toString());
	}
}

interface Interface<T>{
	public abstract void show(T t);
}
//3.1 实现类把接口中的泛型写"死" 的用法
class A implements Interface<String>{//给接口传泛型实参--用具体类型
	@Override
	public void show(String t) {
		System.out.println("死的："+t.toString());
	}
}
//3.2 实现类把接口中的泛型写"活" 的用法
class B<T> implements Interface<T>{//给接口传泛型实参--用泛型类型
	@Override
	public void show(T t) {
		System.out.println("活的："+t.toString());
	}
}