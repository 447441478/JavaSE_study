package cn.hncu.javaSE.倒油.dfs;
/**
 * 倒油---倒油问题解决的过程
 * @author 宋进宇
 */
import java.util.ArrayList;

import cn.hncu.javaSE.倒油.common.OilBucket;

public class PourOil {
	public static void main(String[] args) {
		OilBucket obs[] = new OilBucket[3];
		obs[0] = new OilBucket(12,12);
		obs[1] = new OilBucket(8, 0);
		obs[2] = new OilBucket(5, 0);
		//用来记录每次倒完油后，各个油桶中存在的油
		ArrayList<String> list = new ArrayList<String>();
		//把第一次的情况先记录
		Buckets buckets = new Buckets();
		buckets.setOilBuckets(obs);
		list.add(buckets.toString());
		dfs(buckets, list);//进行深搜
	}
	
	public static void dfs(Buckets buckets,ArrayList<String> list){
		//只要有一个油桶满足存在的油量是  6 就可以了
		for (int k = 0; k < buckets.getOilBuckets().length; k++) {
			if (buckets.getOilBuckets()[k].getExist()==6) {
				print(buckets);
				return ;
			}
		}
		Buckets temp = new Buckets(buckets);
		//进行倒油  把油桶i的油倒入油桶j中
		for (int i = 0; i < temp.getOilBuckets().length; i++) {
			for (int j = 0; j < temp.getOilBuckets().length; j++) {
				if (j==i) {//自己不给自己倒油
					continue;
				}
				int add = temp.getOilBuckets()[j].add(temp.getOilBuckets()[i]);//把油桶i的油倒入油桶j中
				//寻找该次倒油后出现的情况是否出现过
				//如果是第一次出现 则继续倒油 并记录
				if (!list.contains(temp.toString())) {
					temp.setParent(buckets);
					list.add(temp.toString());
					dfs(temp, list);
				}
				//能到这里说明 不是第一次出现 把油倒回去
				temp.getOilBuckets()[j].pourOut(temp.getOilBuckets()[i], add);
			}
		}
	}

	private static void print(Buckets buckets) {
		System.out.println("--------------");
		String str = "";
		while(buckets!=null){
			str = buckets.toString()+str;
			buckets = buckets.getParent();
		}
		System.out.print(str);
	}
}
