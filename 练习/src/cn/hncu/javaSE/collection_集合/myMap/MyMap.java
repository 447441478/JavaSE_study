package cn.hncu.javaSE.collection_集合.myMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
/**
 * Time：2018/4/5
 * Description:我的map，有增、删、改、查功能
 * @author 宋进宇
 *
 */
public class MyMap {
	private Map map = new HashMap();
	/**
	 * 往MyMap集合中添加一个键值对，以前与 key 关联的值，如果没有针对 key 的映射关系，则返回 null。（如果该实现支持 null 值，则返回 null 也可能表示此映射以前将 null 与 key 关联）。 
	 * @param key - 与指定值关联的键
	 * @param value - 与指定键关联的值
	 * @return 以前与 key 关联的值，如果没有针对 key 的映射关系，则返回 null。（如果该实现支持 null 值，则返回 null 也可能表示此映射以前将 null 与 key 关联）。
	 */
	public Object add(Object key,Object value){
		Object oldValue = map.put(key, value);
		return oldValue;
	}
	/**
	 * 通过key值去删除对应的键值对，有就放回value值，没有返回null
	 * @param key - 从映射中移除其映射关系的键 
	 * @return 以前与 key 关联的值；如果没有 key 的映射关系，则返回 null。
	 */
	public Object delete(Object key){
		return map.remove(key);
	}
	
	/**
	 * 返回指定键所映射的值；如果此映射不包含该键的映射关系，则返回 null。
	 * @param key - 要返回其关联值的键
	 * @return 指定键所映射的值；如果此映射不包含该键的映射关系，则返回 null
	 */
	public Object get(Object key){
		return map.get(key);
	}
	/**
	 * 查询所有元素
	 * @return 所有键值对的Map集合
	 */
	public Map getAll(){
		return map;
	}
	/**
	 * 条件查找
	 * @param condition 查找的条件
	 * @return 查找到的结果集
	 */
	public Map getByCondition(String condition){
		Map resMap = new HashMap();
		Set set = map.entrySet();
		for (Object object : set) {
			Entry en = (Entry) object;
			if ((en.getKey().toString()).contains(condition)) {
				resMap.put(en.getKey(), en.getValue());
			}
		}
		return resMap;
	}
}
