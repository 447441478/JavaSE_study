package 软件测试;

import java.util.ArrayList;

public class demo {
	public static void main(String[] args) {
		
		Integer[] a = {1,2,3,4,5};
		String[] strs = {"1","2"};
		Person[] ps = new Person[2];
		ps[0] = new Person();
		ps[0].name = "a";
		ps[1] = new Person();
		ps[1].name = "b";
		Object[] objs ;
		objs = new Person[2];
		objs[0] = new Person();
		objs[1] = new Person();
		
		ps = (Person[])objs;
		for (Object o : ps) {
			System.out.println(o);
		}
		ArrayList<Person> list = new ArrayList<Person>();
		list.add(new Person("a") );
		list.add(new Person("b") );
		Person[] persons = list.toArray(new Person[0]);
		for (Person person : persons) {
			System.out.println(person);
		}
	}
}
class Person{
	String name;
	public Person() {
	}
	public Person(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
}
