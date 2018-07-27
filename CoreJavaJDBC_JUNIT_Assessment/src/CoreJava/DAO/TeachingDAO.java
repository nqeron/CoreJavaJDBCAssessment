package CoreJava.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import CoreJava.Models.Teaching;
import CoreJava.systemsInterfaces.TeachingDAOI;

public class TeachingDAO implements TeachingDAOI {

	public int assignInstructorToCourse(int course_id, int instructor_id) {
		
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
			return -1;
		}
		
		int assigned = -1;
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO teaching (course_id, instructor_id) VALUES (?, ?)");
			ps.setInt(1, course_id);
			ps.setInt(2, instructor_id);
			int upd = ps.executeUpdate();
			if (upd > 0) {
				assigned = upd;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return assigned;
	}

	public List<Teaching> getInstructorsCourses() {
		
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
		
		
		List<Teaching> teachings = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT c.course_name, c.minimum_gpa, i.email, i.full_name FROM teaching t join course c on t.course_id = c.course_id join instructor i on t.instructor_id = i.instructor_id");
			ResultSet rs = ps.executeQuery();
			
			teachings = new ArrayList<Teaching>();
			
			while(rs.next()) {
				Teaching t = new Teaching();
				t.setCourse_name(rs.getString(1));
				t.setMinimum_gpa(rs.getDouble(2));
				t.setEmail(rs.getString(3));
				t.setFull_name(rs.getString(4));
				teachings.add(t);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teachings;
	}

}
