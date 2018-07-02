package net.hncu.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import net.hncu.dao.impl.StudentDaoMySqlImpl;
import net.hncu.domain.Student;

public class StudentDaoMySqlImplTest {
	StudentDaoMySqlImpl mysql = new StudentDaoMySqlImpl();
	@Test
	public void testAdd() throws ParseException {
		Student student = new Student();
		student.setName( "张飞" );
		student.setAge( 18 );
		student.setAddress( "湖南城市学院" );
		student.setTel( "0737-6666666" );
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		student.setBirthday( sdf.parse( "2000-02-03 14:14:14" ) );
			
		Assert.assertTrue( mysql.add( student ) );
	}
	
	@Test
	public void testUpdate(){
		Student student = new Student();
		student.setId( 4 );
		student.setTel( "0737-1111111" );
		Assert.assertTrue( mysql.update( student ) );
	}
	
	@Test
	public void testDeleteById(){
		Assert.assertTrue( mysql.deleteById( 1 ) );
	}
	@Test
	public void testDeleteBatch(){
		Set<Integer> ids = new HashSet<Integer>();
		ids.add( 1 );
		ids.add( 2 );
		ids.add( 3 );
		Assert.assertTrue( mysql.deleteBatch( ids ) );
	}
	
	@Test
	public void testFindById() {
		System.out.println( mysql.findById( 2 ) );
	}
	
	@Test
	public void testFindAll(){
		List<Student> list = mysql.findAll();
		for (Student student : list) {
			System.out.println( student );
		}
	}
	
}
