package CoreJava.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CoreJava.Models.Course;
import CoreJava.Models.Instructor;
import CoreJava.systemsInterfaces.CourseDAOI;

public class CourseDAO implements CourseDAOI {

	public List<Course> getAllCourses() {
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		
		if(conn == null) {
			return null;
		}
		
		//Instantiate instructors
		List<Course> courses = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM course");
			ResultSet rs = ps.executeQuery();
			courses = new ArrayList<Course>();
			while(rs.next()) {
				Course course = new Course();
				course.setCourse_id(rs.getInt(1));
				course.setCourse_name(rs.getString(2));
				course.setMinimum_gpa(rs.getDouble(3));
				courses.add(course);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return courses;
	}

	public List<Course> getCourseByInstructor(int instructor_id) {
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		
		if(conn == null) {
			return null;
		}
		
		//Instantiate instructors
		List<Course> courses = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT c.course_id, c.course_name, c.minimum_gpa FROM course c join teaching t on c.course_id = t.course_id WHERE t.instructor_id = ? ");
			ps.setInt(1, instructor_id);
			ResultSet rs = ps.executeQuery();
			courses = new ArrayList<Course>();
			while(rs.next()) {
				Course course = new Course();
				course.setCourse_id(rs.getInt(1));
				course.setCourse_name(rs.getString(2));
				course.setMinimum_gpa(rs.getDouble(3));
				courses.add(course);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return courses;
	}

}
