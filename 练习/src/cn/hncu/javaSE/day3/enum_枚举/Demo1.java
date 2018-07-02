package cn.hncu.javaSE.day3.enum_枚举;

public class Demo1 {
	public static void main(String[] args) {
		SexEnum s = SexEnum.MALE;
		System.out.println(s);
		System.out.println("-----------------");
		for (SexEnum en : SexEnum.values()) {
			System.out.println(en);
		}
	}
	
}
