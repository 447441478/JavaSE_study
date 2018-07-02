package cn.hncu.javaSE.倒油.bfs;

import cn.hncu.javaSE.倒油.common.OilBucket;
/**
 * Time：2018/3/29
 * Description：存放油桶的数组对象
 * @author 宋进宇
 */

public class Buckets {
	
	private OilBucket[] oilBuckets;//用对象数组存放所有油桶
	private Buckets parent; //记录父节点
	
	/**
	 * 空参构造
	 */
	public Buckets() {
	}
	/**
	 * 拷贝对象的构造函数  ---深拷贝（解除引用赋值捆绑）
	 * @param buckets 被拷贝的对象
	 */
	public Buckets(Buckets buckets) {
		if (buckets==null||buckets.oilBuckets.length==0) {
			return; 
		}
		this.oilBuckets = new OilBucket[buckets.oilBuckets.length];
		for (int i = 0; i < buckets.oilBuckets.length; i++) {
			this.oilBuckets[i] = new OilBucket(buckets.oilBuckets[i].getVolume(), buckets.oilBuckets[i].getExist());
		}
	}
	/**
	 * 获取父节点对象
	 * @return 父节点
	 */
	public Buckets getParent() {
		return parent;
	}
	/**
	 * 设置父节点
	 * @param parent 父节点
	 */
	public void setParent(Buckets parent) {
		this.parent = parent;
	}
	/**
	 * 获取油桶的集合
	 * @return 油桶的集合
	 */
	public OilBucket[] getOilBuckets() {
		return oilBuckets;
	}
	/**
	 * 设置油桶的集合
	 * @param oilBuckets 油桶的集合
	 */
	public void setOilBuckets(OilBucket[] oilBuckets) {
		this.oilBuckets = oilBuckets;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Buckets other = (Buckets) obj;
		///////////以下是根据题目需求来判断是否相等/////////////
		for (int i = 0; i < oilBuckets.length; i++) {
			//只要有一个油桶里面的油量不同 说明两个油桶集合不相同
			if (oilBuckets[i].getExist()!=other.getOilBuckets()[i].getExist()) {
				return false;
			}
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (oilBuckets==null||oilBuckets.length==0) {
			return null;
		}
		sb.append(oilBuckets[0].getExist());
		for (int i = 1; i < oilBuckets.length; i++) {
			sb.append(","+oilBuckets[i].getExist());
		}
		sb.append("\r\n");
		return sb.toString();
	}
	
}
