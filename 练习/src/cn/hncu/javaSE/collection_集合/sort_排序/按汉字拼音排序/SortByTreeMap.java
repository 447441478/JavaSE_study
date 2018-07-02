package cn.hncu.javaSE.collection_集合.sort_排序.按汉字拼音排序;

import java.text.Collator;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * Time：2018/4/5
 * Description：按汉字拼音排序
 * @author 宋进宇
 */
public class SortByTreeMap {
	public static void main(String[] args) {
		//通过创建排序树自定义排序规 去排序。
		Map map = new TreeMap(new Comparator() {
			//通过工厂方法  获取Collator对象  
			private Collator collator = Collator.getInstance();
			@Override
			public int compare(Object o1, Object o2) {
				//通过collator.getCollationKey(String source)方法，
				//去获取 source所对应的语言环境下的CollationKey值，
				//通过collationKey.compareTo(otherCollationKey)去排序
				return collator.getCollationKey((String) o1).compareTo(collator.getCollationKey((String) o2));
			}
		});
		map.put("老王", "20");
		map.put("凤姐", "18");
		map.put("叶良辰", "23");
		map.put("张三", "22");
		map.put("李四", "21");
		Set set = map.entrySet();
		for (Object object : set) {
			Entry en = (Entry) object;
			System.out.println(en.getKey()+","+en.getValue());
		}
		
	}
}
