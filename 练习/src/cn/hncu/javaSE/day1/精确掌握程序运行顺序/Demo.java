package cn.hncu.javaSE.day1.精确掌握程序运行顺序;


public class Demo {
	static Foo fooBar(Foo foo) { //1011   ③
		foo.setX(100);
		return foo;
	}

	public static void main(String[] args) {
		Foo foo = new Foo(300); //假设内存地址：1011   ①
		System.out.println(foo.getX()); //运行结果：300
		Foo fooFoo = fooBar(foo);//1011   ②
		System.out.println(foo.getX() + "-" + fooFoo.getX());//运行结果：100-100
		foo = fooBar(fooFoo);
		System.out.println(foo.getX() + "-" + fooFoo.getX());//运行结果：100-100
	}
	
}

class Foo {
	private int x;//100

	public Foo(int x) {
		this.x = x;
	}

	public void setX(int x) {
		this.x = x; 
		// x=x; //若上一句改成这样，则setX()无效---无法为实例变量赋值
	}

	public int getX() {
		return x;
	}
}

class Father{
	public Father(){
		System.out.println("father的构造");
	}
	static{
		System.out.println("father的静态块");
	}
}
class Child extends Father{
	public Child(){
		System.out.println("child的构造");
	}
	static{
		System.out.println("child的静态块");
	}
}

class A{
	static{
		
	}
	{
		System.out.println("A的属性");
	}
	public A() {
		System.out.println("A的构造方法");
	}
}
class B extends A{
	{
		System.out.println("B的属性");
	}
	public B() {
		System.out.println("B的构造方法");
	}
}
class C extends B{
	{
		System.out.println("C的属性");
	}
	public C() {
		System.out.println("C的构造方法");
	}
}

