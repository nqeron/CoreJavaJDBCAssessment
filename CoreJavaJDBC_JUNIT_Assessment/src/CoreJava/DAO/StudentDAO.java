package CoreJava.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import CoreJava.Models.Student;
import CoreJava.systemsInterfaces.StudentDAOI;

public class StudentDAO implements StudentDAOI {

	public Student getStudentByEmail(String email) {
		Connection conn = null;
		try {
			conn = new OracleConnection().getConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(conn == null) {
			return null;
		}
		
		Student s = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM student where email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				s = new Student();
				s.setStudent_id(rs.getInt(1));
				s.setFull_name(rs.getString(2));
				s.setEmail(rs.getString(3));
				s.setGpa(rs.getDouble(4));
				s.setPass(rs.getString(5));
				s.setStudentRole(rs.getInt(6));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	public Boolean validateUser(String passToValidate, String comparablePas) {
		return passToValidate.equals(comparablePas);
	}

}
