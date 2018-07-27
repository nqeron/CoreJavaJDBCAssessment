package CoreJava.Models;

public class Student {
	
	private int student_id;
	private String full_name;
	private String email;
	private double gpa;
	private String pass;
	private int studentRole;
	
	public Student() {
		this.student_id = 0;
		this.full_name = null;
		this.email = null;
		this.gpa = 0;
		this.pass = null;
		this.studentRole = 0;
	}

	public Student(int student_id, String full_name, String email, double gpa, String pass, int studentRole) {
		super();
		this.student_id = student_id;
		this.full_name = full_name;
		this.email = email;
		this.gpa = gpa;
		this.pass = pass;
		this.studentRole = studentRole;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getStudentRole() {
		return studentRole;
	}

	public void setStudentRole(int studentRole) {
		this.studentRole = studentRole;
	}
	
	
	
	
}
