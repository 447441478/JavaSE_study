package cn.hncu.javaSE.generic_泛型.v2;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GenericAdv {
	@Test//反例
	public void test1(){
		List<Object> objList = new ArrayList<Object>();
		List<String> strList = new ArrayList<String>();
		objList.add("abc");
		strList.add("abc");
//		objList = strList;//WA
	}
}
