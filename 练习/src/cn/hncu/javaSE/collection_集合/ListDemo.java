package cn.hncu.javaSE.collection_集合;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

public class ListDemo {
	@Test
	public void demo1(){
		List list = new ArrayList();
		list.add(1);
		list.add(100);
		list.add(new Person("jack", 11));
		list.add("java");
		list.add(12.3);
		list.add(0);
		list.remove(1);
		ListIterator listIt = list.listIterator();
		
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
	}
}
