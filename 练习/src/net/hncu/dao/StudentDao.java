package net.hncu.dao;

import java.util.List;
import java.util.Set;

import net.hncu.domain.Student;

public interface StudentDao {
	//crud:增删改查操作
	//添加
	public abstract boolean add(Student s);
	//更新
	public abstract boolean update(Student s);
	//删除
	//根据id删除
	public abstract boolean deleteById(Integer id);
	//删除部分,例如：1,4,5,8
	public abstract boolean deleteBatch(Set<Integer> ids);
	//查找
	//根据id查找
	public abstract Student findById(Integer id);
	//查找所有
	//select * from student
	public abstract List<Student> findAll();
	
	
}
