package cn.hncu.javaSE.ioReinforce.serializable;

import java.io.Serializable;

public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int age;
	//测试发现  通过对象流无法存储瞬时数据也就是 关键字 transient 修饰的变量
	private transient String tel;
	
	//测试发现  通过对象流无法存储静态的数据
	public static int count; //记录new了多少个Person 
	
	
	public Person() {
		this( null, 0, null );//调用带参构造方法，为了统一记录
	}
	
	public Person(String name, int age, String tel) {
		super();
		this.name = name;
		this.age = age;
		this.tel = tel;
		count++;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return  name + ", " + age + ", " + tel + ", " + count;
	} 
	
	
}
