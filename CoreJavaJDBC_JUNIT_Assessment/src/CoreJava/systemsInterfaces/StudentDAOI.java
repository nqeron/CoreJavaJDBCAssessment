package CoreJava.systemsInterfaces;

import CoreJava.Models.Student;

public interface StudentDAOI {

	Student getStudentByEmail(String email);

	Boolean validateUser(String pass, String password);
}
