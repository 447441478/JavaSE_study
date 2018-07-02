package cn.hncu.javaSE.collection_集合.testForeach;

import java.util.ArrayList;

import org.junit.Test;

public class Test1 {
	@Test
	public void test1() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("1");
		list.add("2");
		list.add("1");
		for (String string : list) {
			if ("1".equals(string)) {
				list.remove(string);
				// 这里要return 不然会挂
				return;
			}
		}
	}
	//////////////进一步测试//////////////
	@Test
	public void test2() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		for (String string : list) {
			 ///////////////删除倒数第二个，没出现异常///////////////////
			 if ("4".equals(string)) {
				 list.remove(string);
			 }
		}
		for (String string : list) {
//			/////////////// 删除第一个，出现异常///////////////////
//			if ("1".equals(string)) {
//				list.remove(string);
//			}
			///////////// 删除倒数第一个，出现异常///////////////////
			if ("5".equals(string)) {
				list.remove(string);
			}
		}
	}
	//////////////最后测试//////////////
	@Test
	public void test3(){
		ArrayList<String> list = new ArrayList<String>();
		list.add( "1" );
		list.add( "2" );
		list.add( "3" );
		list.add( "4" );
		list.add( "5" );
		for (String string : list) {
			if ( "3".equals( string ) ) {
				list.remove( string );
			}
			System.out.print( string + " ");
		}
		//观察输出可以发现，虽然没有出异常，但是并不是我们想象中的输出:1 2 3 4 5 
		//而是输出:1 2 3 4  
		//测试删除3 输出:1 2 3 
		//测试删除5输出:1 2 3 4 5
		//原因:foreach原理是采用迭代器来实现的。
		//for (String string : list)... 等价于
		/*
		for (Iterator<String> i = list.iterator();i.hasNext();){
			String temp = i.next();//异常根源 因为出现 modCount != expectedModCount 情况
			//https://blog.csdn.net/z55887/article/details/50676548详解
			...
		}
		*/
	}
}
