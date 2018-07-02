package cn.hncu.javaSE.collection_集合.myMap;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class MyMapTest {
	@Test
	public void test1(){
		MyMap map = new MyMap();
		map.add("100", "Jack");
		map.add("000", "Rose");
		map.add("101", "tom");
		map.add("102", "Bob");
		map.add("001", "Alice");
		//查所有
		//Map map2 = map.getAll();
		//条件查
		Map map2 = map.getByCondition("10");
		//查单
		System.out.println(map.get("001"));
		System.out.println("---------------");
		Set set = map2.entrySet();
		for (Object object : set) {
			Entry en = (Entry) object;
			System.out.println(en.getKey()+","+en.getValue());
		}
	}
}
