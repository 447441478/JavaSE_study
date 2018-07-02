package net.hncu.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.hncu.dao.StudentDao;
import net.hncu.domain.Student;
import net.hncu.utils.DbUtils;

public class StudentDaoMySqlImpl implements StudentDao {
	//静态 成员变量 保持单例
	private static DbUtils dbUtils = new DbUtils();
	private static Connection con = dbUtils.getConnection();
	
	@Override
	public boolean add(Student s) {
		//预防空指针异常
		if ( s == null ) {
			return false;
		}
		//采用 PreparedStatement 可以用占位符的形式写好部分SQL语句，后续再完成具体赋值
		String sql = "INSERT INTO tb_student VALUES(?,?,?,?,?,?,?)";
		try {
			//获得 PreparedStatement 对象
			PreparedStatement ps = con.prepareStatement(sql);
			//具体 赋值
			//id
			ps.setInt( 1, s.getId() );
			//姓名
			ps.setString( 2, s.getName() );
			//年龄
			ps.setInt( 3, s.getAge() );
			//地址
			ps.setString( 4, s.getAddress() );
			//电话
			ps.setString( 5, s.getTel() );
			//因为 java 和 MySQL 的 data是不一样的
			//所有 采用 底层存储的数据long型值 进行转换
			//预防空指针
			if ( s.getBirthday() != null ) {
				Date d = new Date( s.getBirthday().getTime() );
				ps.setDate( 6, d );
			}
			// 学院 先默认 6 
			ps.setInt( 7, 6 );
			int i = ps.executeUpdate();
			//如果 i>0 说明 添加成功，否则 添加失败
			if ( i > 0 ) {
				return true;
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean update(Student s) {
		//预防 空指针 和 没 id值学生 进行修改
		if ( s == null || s.getId() <= 0 ) {
			return false;
		}
		//因为 要频繁的 对String 进行 修改
		//所有采用  StringBuilder 对象 节省 内存
		StringBuilder sql = new StringBuilder( "UPDATE tb_student SET " );
		//如果 姓名不为空 就说明要修改
		if ( s.getName() != null ) {
			sql.append( "name = '" + s.getName() +"'" );
		}
		//如果 年龄大于0  就说明要修改
		if ( s.getAge() > 0 ) {
			sql.append( "age = " + s.getAge() );
		}
		//如果 地址不为空 就说明要修改
		if ( s.getAddress() != null ) {
			sql.append( "address = '" + s.getAddress() + "'");
		}
		//如果 电话不为空 就说明要修改
		if ( s.getTel() != null ) {
			sql.append( "tel = '" + s.getTel() + "'" );
		}
		////如果 出生日期不为空 就说明要修改
		if ( s.getBirthday() != null ) {
			sql.append( "birthday = " + new Date( s.getBirthday().getTime() ) );
		}
		//补全SQL语句
		sql.append( " WHERE id = " + s.getId() );
		try {
			PreparedStatement ps = con.prepareStatement( sql.toString() );
			//执行 SQL语句
			int i = ps.executeUpdate();
			//如果 i>0 说明 修改成功，否则修改失败
			if ( i > 0 ) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean deleteById(Integer id) {
		//预防 空指针 和 没 id值学生 进行删除
		//这种情况默认 删除成功 反正没这种数据
		if ( id == null || id <= 0) {
			return true;
		}
		//SQL语句，待补全
		String sql = "DELETE FROM tb_student WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement( sql );
			//补全id
			ps.setInt( 1, id );
			//执行 SQL语句
			int i = ps.executeUpdate();
			//如果 i>0 说明 删除成功，否则删除失败
			if ( i > 0 ) {
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean deleteBatch(Set<Integer> ids) {
		//默认 空指针 或者 Set 为空 返回 true 
		if ( ids == null || ids.size() <= 0 ) {
			return true;
		}
		//定义好部分SQL语句，后续再完善
		StringBuilder sql = new StringBuilder( "DELETE FROM tb_student WHERE id IN ( " );
		//采用  id IN (...) 语法，需要进行特殊处理，具体如下
		boolean isFirst = true;
		for (Integer integer : ids) {
			
			if ( isFirst ) {//如果是第一个 就 直接 追加在后面
				sql.append( integer );
				isFirst = false;
			} else {//否则的话需要进行 补 ','
				sql.append( ", " + integer );
			}
		}
		//最后完善整个 SQl 语句
		sql.append( " )" );
		try {
			Statement statement = con.createStatement();
			//执行 SQL 语句
			int i = statement.executeUpdate( sql.toString() );
			//如果 i>0 说明批量删除成功，否则失败
			if ( i > 0 ) {
				return true;
			}
			//能到这里 说明 删除失败
			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public Student findById(Integer id) {
		//预防 空指针 ，默认返回 null
		if ( id == null ) {
			return null;
		}
		//定义 部分 SQL 语句
		String sql = "SELECT * FROM tb_student WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement( sql );
			//补全SQL 语句
			ps.setInt( 1, id );
			//执行SQL语句，并获得查询结果集
			ResultSet resultSet = ps.executeQuery();
			//如果 结果集有数据 就进行 数据封装，并返回 值对象
			while ( resultSet.next() ) {
				//进行数据封装
				Student student = new Student();
				student.setId( resultSet.getInt( 1 ) );
				student.setName( resultSet.getString( 2 ) );
				student.setAge( resultSet.getInt( 3 ) );
				student.setAddress( resultSet.getString( 4 ) );
				student.setTel( resultSet.getString( 5 ) );
				//预防空指针异常
				if ( resultSet.getDate( 6 ) != null) {
					//需要进行转换
					student.setBirthday( new java.util.Date( resultSet.getDate( 6 ).getTime() ) );
				}
				student.setCollege( resultSet.getInt( 7 ) );
				return student;
			}
			//能到这里说明没有数据
			return null;
		} catch (SQLException e) {
			return null;
		}
		
	}

	@Override
	public List<Student> findAll() {
		//先创建一个结果集
		List<Student> list = new ArrayList<Student>();
		//定义SQL语句
		String sql = "SELECT * FROM tb_student ";
		try {
			PreparedStatement ps = con.prepareStatement( sql );
			//执行 SQL 语句，并获得结果集
			ResultSet resultSet = ps.executeQuery();
			//如果结果集有数据，就进行数据封装 并添加到结果集
			while ( resultSet.next() ) {
				//进行数据封装
				Student student = new Student();
				student.setId( resultSet.getInt( 1 ) );
				student.setName( resultSet.getString( 2 ) );
				student.setAge( resultSet.getInt( 3 ) );
				student.setAddress( resultSet.getString( 4 ) );
				student.setTel( resultSet.getString( 5 ) );
				//预防空指针异常
				if ( resultSet.getDate( 6 ) != null) {
					student.setBirthday( new java.util.Date( resultSet.getDate( 6 ).getTime() ) );
				}
				student.setCollege( resultSet.getInt( 7 ) );
				//加入结果集
				list.add( student );
			}
			//返回结果集
			return list;
		} catch (SQLException e) {
			//如果出异常就返回null
			return null;
		}
	}
	

}
