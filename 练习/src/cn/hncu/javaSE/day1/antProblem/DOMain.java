package cn.hncu.javaSE.day1.antProblem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 有一根27厘米的细木杆，在第3厘米、7厘米、11厘米、18厘米、23厘米这五个位置上各有一只蚂蚁。
 木杆很细，不能同时通过两只蚂蚁。开始时，蚂蚁的头朝左还是朝右是任意的，它们只会朝前走或调头，
 但不会后退。当任意两只蚂蚁碰头时，两只蚂蚁会同时调头朝反方向走。
 假设蚂蚁们每秒钟可以走一厘米的距离。编写程序，求所有蚂蚁都离开木杆的最小时间和最大时间。
要求：用类模拟出蚂蚁的行为特性，进而模拟出五只蚂蚁在木杆上的运行过程来编程求解。
不能通过数学的方式直接用公式计算。
 */
public class DOMain {
	public static int min = Integer.MAX_VALUE;
	public static int max = Integer.MIN_VALUE;
	public static int num = 0;
	
	public static void main(String[] args) {
		int pos[] = {3,7,11,18,23};
		List<Ant> list = new ArrayList<Ant>();
		list.add(new Ant(0));
		list.add(new Ant(27));
		boolean direction[] = new boolean[pos.length];//表示初始方向
		fun(direction,list,pos,0);
		System.out.println("min:"+min/2+" max:"+max/2);
	}

	private static void fun(boolean[] direction, List<Ant> list, int[] pos, int i) {
		if (i==direction.length) {
			num++;
			System.out.println("第"+num+"种情况：");
			for (int j = 0; j <pos.length; j++) {
				Ant ant = new Ant(pos[j]);
				ant.setId(j+1);
				ant.setLeft(direction[j]);
				list.add(j+1, ant);
			}
			int count = 0;
			while(list.size()>2){
				count++;
				for (int j = 1; j < list.size()-1; j++) {
					if(list.get(j-1).getPosition()>=list.get(j).getPosition()){
						list.get(j).setLeft(false);
						System.out.println("蚂蚁"+list.get(j).getId()+"与蚂蚁"+list.get(j-1).getId()+"相遇于"+list.get(j).getPosition());
					}
					if (list.get(j+1).getPosition()<=list.get(j).getPosition()) {
						list.get(j).setLeft(true);
						System.out.println("蚂蚁"+list.get(j).getId()+"与蚂蚁"+list.get(j+1).getId()+"相遇于"+list.get(j).getPosition());
					}
				}
				for (int j = list.size()-2; j >0; j--) {
					list.get(j).move();
					if (count%2==0) {
						System.out.println("蚂蚁"+list.get(j).getId()+"位置:"+list.get(j).getPosition());
						if (j==list.size()-2&&list.get(j).getPosition()==27) {
							System.out.println("蚂蚁"+list.get(j).getId()+"从右侧走出:");
							list.remove(j);
							continue;
						}
						if (j==1&&list.get(j).getPosition()==0) {
							System.out.println("蚂蚁"+list.get(j).getId()+"从左侧走出:");
							list.remove(j);
							continue;
						}
					}
				}
			}
			System.out.println();
			if (count>max) {
				max = count;
			}
			if (count<min) {
				min = count;
			}
			return;
		}
		direction[i]=true;
		fun(direction, list, pos, i+1);
		direction[i]=false;
		fun(direction, list, pos, i+1);
	}

}
