package cn.hncu.javaSE.倒油.common;

/**
 * Time:2018/3/29 
 * Description：油桶---倒油问题所需对象
 * @author 宋进宇
 *
 */
public class OilBucket {
	private int volume;// 油桶的容量
	private int exist;// 油桶里已有的油

	public OilBucket() {
	}
	/**
	 * 构造一个油桶
	 * @param volume 油桶的容量
	 * @param exist 油桶里已有的油
	 */
	public OilBucket(int volume, int exist) {
		super();
		this.volume = volume;
		this.exist = exist;
	}

	/**
	 * 把油桶ob中油倒入当前对象中
	 * 
	 * @param ob 倒出油的对象
	 *            
	 * @return 表示倒入油的数量
	 */
	public int add(OilBucket ob) {
		if (ob == null) {
			return 0;
		}
		int add = 0;
		if (volume>=exist+ob.exist) {
			add=ob.exist;
			exist += ob.exist;
			ob.exist =0;
		}else{
			add = volume-exist;
			ob.exist -= add;
			exist = volume;
		}
		return add;
	}

	/**
	 * 把当前对象中的油倒入ob中
	 * 
	 * @param ob 被倒入油的对象
	 * 
	 * @return 表示倒出油的数量
	 */
	public void pourOut(OilBucket ob,int value) {
		if (ob==null) {
			return ;
		}
		exist -= value;
		ob.exist += value;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getExist() {
		return exist;
	}

	public void setExist(int exist) {
		this.exist = exist;
	}
}
