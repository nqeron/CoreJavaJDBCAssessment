package CoreJava.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CoreJava.Models.Instructor;
import CoreJava.systemsInterfaces.InstructorDAOI;

public class InstructorDAO implements InstructorDAOI {

	public List<Instructor> getAllInstructors() {
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
		List<Instructor> instructors = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM instructor");
			ResultSet rs = ps.executeQuery();
			instructors = new ArrayList<Instructor>();
			while(rs.next()) {
				//Read instructor data from database
				Instructor instructor = new Instructor();
				instructor.setInstructor_id(rs.getInt(1));
				instructor.setFull_name(rs.getString(2));
				instructor.setEmail(rs.getString(3));
				instructor.setSpecialty(rs.getString(4));
				instructor.setAdminRole(rs.getInt(5));
				instructor.setPass(rs.getString(6));
				
				//add to list
				instructors.add(instructor);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instructors;
	}

	public Instructor getInstructorByEmail(String email) {
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
		Instructor instructor = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM instructor WHERE email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			instructor = new Instructor();
			if(rs.next()) {
				//Read instructor data from database
				instructor = new Instructor();
				instructor.setInstructor_id(rs.getInt(1));
				instructor.setFull_name(rs.getString(2));
				instructor.setEmail(rs.getString(3));
				instructor.setSpecialty(rs.getString(4));
				instructor.setAdminRole(rs.getInt(5));
				instructor.setPass(rs.getString(6));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instructor;
	}
	
	public String validateUser(Instructor ins, String comparablePas) {
		if(ins.equals(new Instructor())) {
			return "Wrong Credentials";
		}
		if(!ins.getPass().equals(comparablePas)) {
			return "Wrong Credentials";
		}
		
		if(ins.getAdminRole() == 0) {
			return "Instructor";
		} else if(ins.getAdminRole() == 1) {
			return "Admin";
		}
		
		return null;
	}

}
