package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Student;

public class Database {

	private static List<Student> students = new ArrayList<Student>();

	public static void add(Student student) {
		Database.students.add(student);
	}

	public static void remove(Integer registration) {
		Database.students.removeIf(student -> student.getRegistration().equals(registration));
	}

	public static List<Student> getStudents() {
		return Database.students;
	}

	public static Student getStudent(Integer registration) {
		Optional<Student> optionalStudent = Database.students.stream()
				.filter(student -> student.getRegistration().equals(registration)).findAny();

		if (optionalStudent.isPresent()) {
			return optionalStudent.get();
		}
		return null;
	}
}
