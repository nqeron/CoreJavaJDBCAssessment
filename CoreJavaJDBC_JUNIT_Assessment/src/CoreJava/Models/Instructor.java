package CoreJava.Models;

public class Instructor {

	private int instructor_id;
	private String full_name;
	private String email;
	private String specialty;
	private int adminRole;
	private String pass;
	
	public Instructor() {
		this.instructor_id = 0;
		this.full_name = null;
		this.email = null;
		this.specialty = null;
		this.adminRole = 0;
		this.pass = null;
	}

	public Instructor(int instructor_id, String full_name, String email, String specialty, int adminRole, String pass) {
		super();
		this.instructor_id = instructor_id;
		this.full_name = full_name;
		this.email = email;
		this.specialty = specialty;
		this.adminRole = adminRole;
		this.pass = pass;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Instructor)) {
			return false;
		}
		Instructor instOther = (Instructor) o;
		boolean ids = this.instructor_id == instOther.instructor_id;
		boolean names = (this.full_name == null && instOther.full_name == null) || this.full_name.equals(instOther.full_name);
		boolean emails =(this.email == null && instOther.email == null) || this.email.equals(instOther.email);
		boolean specialties = (this.specialty == null && instOther.specialty == null) || this.specialty.equals(instOther.specialty);
		boolean adminRoles = this.adminRole == instOther.adminRole;
		boolean passes = (this.pass == null && instOther.pass == null) || this.pass.equals(instOther.pass);
		return ids && names && emails && specialties && adminRoles && passes;
	}

	public int getInstructor_id() {
		return instructor_id;
	}

	public void setInstructor_id(int instructor_id) {
		this.instructor_id = instructor_id;
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

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public int getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(int adminRole) {
		this.adminRole = adminRole;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
	
}
