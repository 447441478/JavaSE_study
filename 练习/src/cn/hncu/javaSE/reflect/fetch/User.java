package cn.hncu.javaSE.reflect.fetch;

public class User {
	private String id;
	private String name;
	private int age;
	
	
	public int i; //这个属性 只是为了演示 分解Filed 时用的，没有实际意义
	double d; //这个属性 只是为了演示 分解Filed 时用的，没有实际意义
	
	public User() {
	}

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public User(String id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	//这个函数 没有 实际意义 为了演示 分解Method 
	public double sum( int x, double y ) throws NumberFormatException,IllegalArgumentException {
		return x+y;
	}
	//这个函数 没有 实际意义 为了演示 分解Method 
	public static void print(){
		System.out.println("只是起演示作用，没有实际意义。");
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	
	
	
	
}
