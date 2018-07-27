package CoreJava.systemsInterfaces;

import java.util.List;

import CoreJava.Models.Teaching;

public interface TeachingDAOI {

	int assignInstructorToCourse(int course_id, int instructor_id); //matching to what's in the main
	List<Teaching> getInstructorsCourses();
	
}
