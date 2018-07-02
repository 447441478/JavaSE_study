package cn.hncu.javaSE.design_设计模式.VO_值对象;
import java.io.Serializable;
/**
 * Time：2018/4/15
 * Description：
 * 值对象取名规范：xxxValueObject、xxxVO、xxxModel
 * 或者是在一个存放值对象包下直接取名xxx,
 * 步骤：
 * 	1.实现序列化接口
 * 	2.私有化成员变量
 * 	3.保留一个空参构造函数
 * 	4.对私有化的成员变量写set、get方法，注意：boolean类型的get方法是isXxx。
 * 	5.写上equal和hashCode方法
 * 	6.写上toString方法。
 * @author 宋进宇
 */
//最好实现序列化接口
public class UserModel implements Serializable {
	private String id;//id 一般设为主键
	private String userName;//用户名
	private Integer age;//年龄
	private String pwd;//密码
	private boolean admin; //是否为管理员 boolean型的变量名不要取为 “isXxx” 
						   //因为生成的geter 函数时isXxx()，以后在框架里面会有问题。
	//记得保留一个空参构造。
	public UserModel() {
	}
	
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserModel other = (UserModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + ", " + userName + ", " + age ;
	}
}
