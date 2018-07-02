package cn.hncu.javaSE.collection_集合;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class SetDemo {
	@Test
	public void demo(){
		Set set = new HashSet();
		set.add(1);
		set.add(100);
		set.add("java");
		set.add(new Person("Jack", 21));
		set.add(new Person("Tom", 23));
		set.add(new Person("Rose", 22));
		for (Object object : set) {
			System.out.println(object);
		}
	}
}
