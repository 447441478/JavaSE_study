package cn.hncu.javaSE.day1;
public class Person {
	private String name="Jack";//4
	private int age=2;//5
	public Person() {//3 此处先执行super(...),再返回到当前类往下执行
		age=1000;//6
	}
	public static void main(String[] args) {//1
		Person p = new Person();//2
		System.out.println(p.name+","+p.age);//7
	}
}