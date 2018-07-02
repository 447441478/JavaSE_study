package cn.hncu.javaSE.bigNumber;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * Time：2018/4/5
 * Description：BigDecimal类的学习
 * @author 宋进宇
 */
public class BigDecimalDemo {
	@Test
	public void test1(){
		double sum = 0.0;
		for (int i = 0; i < 10; i++) {
			sum+=0.1;
		}
		System.out.println(sum);
		//结果为0.9999999999999999
		//按常理来说结果应该为1.0，但是由于浮点数运算结果有时会是近似值，
		//有时会是正确值，比如当i等于5是结果为：0.5是想要的结果。
		//因此我们可以采用BigDecimal来进行处理，它可以精确计算
	}
	@Test
	public void test2(){
		BigDecimal bd = new BigDecimal(0);
		for (int i = 0; i < 10; i++) {
			bd = bd.add(BigDecimal.valueOf(0.1));
			//bd = bd.add(new BigDecimal(0.1));
		}
		System.out.println(bd);
		//结果为1.0是我们想要的结果
		//但是要注意，使用BigDecimal时，add()方法里面的参数应该用BigDecimal.valueOf(0.1)
		//如果参数用new BigDecimal(0.1)会出现
		//1.0000000000000000555111512312578270211815834045410156250
		//所以使用BigDecimal进行运算时，参数用BigDecimal.valueOf(0.1)这种方式。
	}
}
