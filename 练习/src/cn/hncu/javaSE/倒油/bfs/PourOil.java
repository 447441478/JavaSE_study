package cn.hncu.javaSE.倒油.bfs;

import cn.hncu.javaSE.倒油.common.OilBucket;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Time：2018/3/29
 * Description：进行解决倒油问题的主类
 * @author 宋进宇
 */
public class PourOil {

	public static void main(String[] args) {
		Buckets buckets=init();//初始化化根节点
		//广搜必备的队列
		Queue<Buckets> que = new LinkedList<Buckets>();
		que.add(buckets);
		//用来记录树所有节点
		ArrayList<Buckets> list = new ArrayList<Buckets>();
		list.add(buckets);
		bfs(que,list);
	}
	/**
	 * 广搜 --- 找出最短倒出6斤油的路径
	 * @param que 进行广搜的辅助队列
	 * @param list 存放每个节点的情况
	 */
	private static void bfs(Queue<Buckets> que, ArrayList<Buckets> list) {
		//取出队首
		Buckets buckets = que.poll();
		if (buckets==null) {
			return ;
		}
		//只要有一个油桶的油量为6斤就打印路径
		for (int i = 0; i < buckets.getOilBuckets().length; i++) {
			if (buckets.getOilBuckets()[i].getExist()==6) {
				print(buckets);
				return;
			}
		}
		//备份节点
		Buckets temp = new Buckets(buckets);
		//buckets 的子节点
		//油桶i给油桶j倒油
		for (int i = 0; i < temp.getOilBuckets().length; i++) {
			for (int j = 0; j < temp.getOilBuckets().length; j++) {
				if (i==j) {//自己给自己倒油没意义
					continue;
				}
				//i给j倒油
				int add = temp.getOilBuckets()[j].add(temp.getOilBuckets()[i]);
				if (add==0) {//如果倒出空就与  buckets 情况相同，即跳过
					continue;
				}
				//倒出油的情况
				Buckets newBuckets = new Buckets(temp);
				//如果先前没有出现过这种情况，则是该树节点加人队列
				if (!list.contains(newBuckets)) {
					newBuckets.setParent(buckets);
					list.add(newBuckets);
					que.add(newBuckets);
				}
				//把油倒回去，继续找  buckets 的子节点
				temp.getOilBuckets()[j].pourOut(temp.getOilBuckets()[i],add);
			}
		}
		bfs(que, list);
	}
	/**
	 * 打印一条路径
	 * @param buckets 该路径的叶子节点  也就是 最底层的节点
	 */
	private static void print(Buckets buckets) {
		if (buckets==null) {
			return ;
		}
		Buckets parent = buckets.getParent();
		StringBuilder sb = new StringBuilder(buckets.toString());
		while(parent!=null){
			sb.insert(0, parent.toString());
			parent = parent.getParent();
		}
		System.out.println(sb.toString());
	}
	/**
	 * 初始化油桶的集合
	 * @return 油桶的集合
	 */
	private static Buckets init() {
		Buckets buckets = new Buckets();
		OilBucket obs[] =new OilBucket[3];
		obs[0] = new OilBucket(12, 12);
		obs[1] = new OilBucket(8, 0);
		obs[2] = new OilBucket(5, 0);
		buckets.setOilBuckets(obs);
		return buckets;
	}
}
