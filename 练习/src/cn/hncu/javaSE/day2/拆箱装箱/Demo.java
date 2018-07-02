package cn.hncu.javaSE.day2.拆箱装箱;

import java.util.Scanner;

import org.junit.Test;

public class Demo {
	
	@Test
	public void demo1(){
		Integer a = 5; //装箱  Java自动帮我们把5封装成一个Integer对象赋给引用变量a
		               // a = new Integer(5)
		System.out.println(a);
		
		int  b = a; //拆箱   Java自动帮我们把对象a中的整数值取出来赋给变量b
		System.out.println( b+5 );
		
	}
	
	//凡是包装类对象和基本数据类型进行比较，都会自动拆箱
	//对于Integer包装类，如果数据值在一个字节[-128,127]范围内，存储在栈中，超出该范围存储在堆中
	@Test
	public void demo2(){
		Integer i1 = 100;//装箱
		System.out.println( i1==100 ); //true
		
		Integer i2 = 100;//装箱
		//在自动装箱时对于值从–128到127之间的值，它们被装箱为Integer对象后，会存在内存中被重用。
		System.out.println( i1==i2 ); //true
		
		
		Integer i3 = 200;
		Integer i4 = 200;
		
		//如果超过了从–128到127之间的值，被装箱后的Integer对象并不会被重用。
		System.out.println( i3==i4 );//false
		
		System.out.println( i3==200 );//true
	}
	
	/*
	 * 凡是 new出来的字符串，不能用"=="来判断它是否等于另一个字符串，因为都是false
	 * 凡是String类中返回字符串的方法，所返回的字符串都是重新new出来的
	 * 以后判断String都有equals()
	 * 
	 */
	
	@Test
	public void demo3(){
		String s1="2";
		String s2="2";
		System.out.println(s1==s2);//true
		String s3 = new String("2");
		System.out.println(s1==s3);//false
		
		String s4 = "23".substring(0, 1);
		System.out.println("s4="+s4);
		System.out.println( s1==s4 );//false
		System.out.println( s3==s4 );//false
		System.out.println( s4=="2");//false
		
		Scanner sc = new Scanner(System.in);
		String name=sc.nextLine();
		//if(name=="jack"){//bug: 这一句永远不会出现true
		if(name.equals("jack")){//这一句在name可能会是null的情况下，有bug
		//没有bug的写法: if(name!=null && name.equals("jack")){ //更好的写法: if( "jack".equals(name) )
		   System.out.println("OK...");
		}
		
	}
}
