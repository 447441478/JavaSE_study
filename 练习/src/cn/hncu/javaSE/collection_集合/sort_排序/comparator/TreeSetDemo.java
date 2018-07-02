package cn.hncu.javaSE.collection_集合.sort_排序.comparator;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetDemo {
	public static void main(String[] args) {
		Set set = new TreeSet(new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				if (o1 instanceof Person && o2 instanceof Person) {
					return ((Person)o1).getAge()-((Person)o2).getAge();
				}
				return 1;
			}
		});
		set.add(new Person("Jack", 44));
		set.add("abc");
		set.add(new Person("Tom", 33));
		set.add(new Person("Rose", 22));
		set.add(123);
		for (Object object : set) {
			System.out.println(object);
		}
		
	}
}
