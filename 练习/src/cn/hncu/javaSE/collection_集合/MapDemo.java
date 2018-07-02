package cn.hncu.javaSE.collection_集合;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class MapDemo {
	@Test
	public void demo(){
		Map map = new HashMap();
		map.put("1001", new Person("Tom", 55));
		map.put("1002", "Java");
		map.put("105", new Person("Rose", 20));
		map.put("1004", new Person("Jack", 22));
		
		Set set = map.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext()){
			Entry en = (Entry) it.next();
			System.out.println(en.getKey()+","+en.getValue());
		}
		
		System.out.println("-----------------");
		Set set2 = map.keySet();
		for (Object object : set2) {
			System.out.println(object+","+map.get(object));
		}
		
		System.out.println("-----------------");
		Collection col = map.values();
		Object[] array = col.toArray();
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		
	}
}
