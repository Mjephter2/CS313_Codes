package lecture12;

import java.time.LocalDate;
import java.util.Comparator;

public class Comparisons {

	public static void main(String[] args) {

		Student s1 = new Student("Alex", "Zaroni", 3.25, LocalDate.now());
		Student s2 = new Student("Barbara", "Yeltsin", 3.78, LocalDate.now());
		Student s3 = new Student("Cameron", "Xavier", 2.33, LocalDate.now());
		
		System.out.println("Comparison using Student.compareTo (lastname alphabetical)");
		int comparison1 = s1.compareTo(s2);
		if (comparison1 < 0) {
			System.out.println(s1 + " comes before " + s2);
		} else if (comparison1 > 0) {
			System.out.println(s1 + " comes after " + s2);
		} else {
			System.out.println(s1 + " and " + s2 + " are equivalent in the ordering");
		}
		
		int comparison2 = s3.compareTo(s2);
		if (comparison2 < 0) {
			System.out.println(s3 + " comes before " + s2);
		} else if (comparison1 > 0) {
			System.out.println(s3 + " comes after " + s2);
		} else {
			System.out.println(s3 + " and " + s2 + " are equivalent in the ordering");
		}
		
		System.out.println("\nComparison using StudentComparator.compare (GPA ascending)");
		StudentComparator comp = new StudentComparator();
		int comparison3 = comp.compare(s1, s2);
		if (comparison3 < 0) {
			System.out.println(s1 + " comes before " + s2);
		} else if (comparison1 > 0) {
			System.out.println(s1 + " comes after " + s2);
		} else {
			System.out.println(s1 + " and " + s2 + " are equivalent in the ordering");
		}
	
	}

}


class Student implements Comparable<Student> {
	
	private String lastname, firstname;
	private double gpa;
	private LocalDate enrollmentDate;
	
	public Student(String f, String l, double gpa, LocalDate enrolled) {
		this.lastname = l;
		this.firstname = f;
		this.gpa = gpa;
		this.enrollmentDate = enrolled;
	}
	
	public double getGPA() {
		return this.gpa;
	}

	@Override
	public int compareTo(Student other) {
		return this.lastname.compareTo(other.lastname);
	}
	
	@Override
	public String toString() {
		return String.format("%s %s (gpa: %.2f, enrolled: %s)", this.firstname, this.lastname, this.gpa, this.enrollmentDate);
	}
}

class StudentComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		if (o1.getGPA() < o2.getGPA()) return -1;
		else if (o1.getGPA() > o2.getGPA()) return 1;
		else return 0;
	}
	
}