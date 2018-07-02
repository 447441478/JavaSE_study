package cn.hncu.javaSE.collection_集合.sort_排序.comparator;

public class Person implements Comparable {
	private String name;
	private int age;
	public Person() {
	}
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
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
	@Override
	public String toString() {
		return name + "," + age ;
	}
	@Override
	public int compareTo(Object o) {
		Person other = (Person)o;
		return age-other.age;
	}
	
}
