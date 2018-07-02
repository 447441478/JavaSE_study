package net.hncu.domain;

import java.util.Date;

public class Student {
	private int id;
	private String name;
	private int age;
	private String address;
	private String tel;
	private Date birthday;	
	private int college;
	
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Student(String name, int age, String address, String tel, int id,
			Date birthday) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
		this.tel = tel;
		this.id = id;
		this.birthday = birthday;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getCollege() {
		return college;
	}

	public void setCollege(int college) {
		this.college = college;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", address=" + address + ", tel=" + tel
				+ ", birthday=" + birthday + ", college=" + college + "]";
	}
	
}
