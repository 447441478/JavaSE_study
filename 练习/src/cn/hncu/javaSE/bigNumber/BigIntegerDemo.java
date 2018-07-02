package cn.hncu.javaSE.bigNumber;

import java.math.BigInteger;

import org.junit.Test;
/**
 * Time：2018/4/5
 * Description：学习BigInteger类
 * @author 宋进宇
 */
public class BigIntegerDemo {
	@Test
	public void test1(){
		long l = factorial(50);
		System.out.println(l);
		//-3258495067890909184 输出结果为负数，说明结果超过long型值的范围
	}
	/**
	 * 基本数据类型求阶乘i！
	 * @param i-阶乘因子
	 * @return 阶乘结果
	 */
	private long factorial(int i) {
		long l=1;
		for (int j = 2; j <= i; j++) {
			l*=j;
		}
		return l;
	}
	
	@Test
	public void test2(){
		BigInteger bi = factorial2(50);
		System.out.println(bi);
		//30414093201713378043612608166064768844377641568960512000000000000
		//结果正确
	}
	/**
	 * BigInteger求阶乘i！
	 * @param i-阶乘因子
	 * @return 阶乘结果
	 */
	private BigInteger factorial2(int i) {
		BigInteger bi = BigInteger.ONE;
		for (int j = 2; j <= i; j++) {
			bi = bi.multiply(BigInteger.valueOf(j));
		}
		return bi;
	}
}
