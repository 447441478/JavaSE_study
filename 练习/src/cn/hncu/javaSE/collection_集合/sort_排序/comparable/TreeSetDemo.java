package cn.hncu.javaSE.collection_集合.sort_排序.comparable;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetDemo {
	public static void main(String[] args) {
		Set set = new TreeSet();
		set.add(new Person("Jack", 22));
		set.add(new Person("Tom", 33));
		set.add(new Person("Rose", 11));
		for (Object object : set) {
			System.out.println(object);
		}
		
	}
}
