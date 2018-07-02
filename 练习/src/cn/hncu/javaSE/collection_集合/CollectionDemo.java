package cn.hncu.javaSE.collection_集合;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

public class CollectionDemo {
	@Test
	public void demo1(){
		Collection col = new ArrayList();
		col.add(1);
		col.add(100);
		col.add(new Person("java", 13));
		col.add("abc");
		col.add(12.34);
		Iterator it = col.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		System.out.println("--------------");
		for (Object object : col) {
			System.out.println(object);
		}
		Collection col2 =new ArrayList();
		col2.add(1);
		col.addAll(col2);
		for (Object object : col2) {
			System.out.println(object);
		}
		System.out.println("----------");
		col2.add("abc");
		col.removeAll(col2);
		for (Object object : col) {
			System.out.println(object);
		}
	}
}
