package CoreJava.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import CoreJava.CustomExceptions.StudentRegistrationException;
import CoreJava.Models.Attending;
import CoreJava.Models.Course;
import CoreJava.Models.Student;
import CoreJava.systemsInterfaces.AttendingDAOI;

public class AttendingDAO implements AttendingDAOI {

	public int registerStudentToCourse(Student student, Course course) throws StudentRegistrationException {
		
		if(student.getGpa() < course.getMinimum_gpa()) {
			throw new StudentRegistrationException("\nDid not meet minimum GPA requirement\nRegistration Denied");
		}
		
		//Establish a connection
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn == null) {
			return 0;
		}
	
		int key = 0;
		try {
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM attending WHERE course_id = ? AND student_id=?");
			ps.setInt(1, course.getCourse_id());
			ps.setInt(2, student.getStudent_id());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				conn.close();
				throw new StudentRegistrationException("Student is already registered for that course!");
			}
			//Insert course_id, student_id into attending table
			String[] attid = {"attending_id"};
			ps = conn.prepareStatement("INSERT INTO attending (course_id, student_id) VALUES (?, ?)", attid);
			ps.setInt(1, course.getCourse_id());
			ps.setInt(2, student.getStudent_id());
			ps.executeUpdate();
			
			//retrieve generated primary key
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				key = rs.getInt(1);
			}
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;
	}

	public List<Attending> getStudentCourse(int student_id) {
		//Gets instructor and course data for all the courses that a given student is attending
		
		//Establish a connection
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn == null) {
			return null;
		}
		
		List<Attending> attendings = null;
		try {
			//left joins are needed since teaching or instructor can be null
			PreparedStatement ps = conn.prepareStatement("SELECT c.course_name, i.email, i.full_name FROM attending att join course c on att.course_id = c.course_id left join teaching t on att.course_id = t.course_id left join instructor i on t.instructor_id = i.instructor_id WHERE att.student_id = ?");
			ps.setInt(1, student_id);
			ResultSet rs = ps.executeQuery();
			
			attendings = new ArrayList<Attending>();
			while(rs.next()) {
				Attending att = new Attending();
				att.setCourse_name(rs.getString(1));
				att.setEmail(rs.getString(2));
				att.setFull_name(rs.getString(3));
				attendings.add(att);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return attendings;
		
	}

}
