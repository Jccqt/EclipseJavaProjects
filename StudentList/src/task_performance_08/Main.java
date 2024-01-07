package task_performance_08;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	public static void main(String[]args) {
		Scanner input = new Scanner(System.in);
		List<String> studentNums = new LinkedList<>();
		List<String> firstNames = new LinkedList<>();
		Map<String,String>students = new HashMap<>(); // empty hashmap for students
		
		String studentNumber, firstName;
		int studentCounter = 0;
		
		for(int i = 1; i <= 3; i++) {
			// user input for student number
			System.out.print("Enter student number " + i + ": ");
			studentNumber = input.next();
			
			while(!studentNumChecker(studentNumber)) {
				System.out.print("Invalid student number! Please input a valid student number: ");
				studentNumber = input.next();
			}
			
			studentNums.add(studentNumber);
			
			// user input for student first name
			System.out.print("Enter first name " + i + ": ");
			firstName = input.next();
			
			while(!firstNameChecker(firstName)) {
				System.out.print("Invalid first name! Please input a valid first name: ");
				firstName = input.next();
			}
			
			firstNames.add(firstName);
			
			// will insert the elements from studentNums and firstNames list into studentsMap
			students.put(studentNumber, firstName);
			studentCounter++;
		}
		
		System.out.println("Student List:");
		for(Map.Entry e : students.entrySet()) {
			System.out.println(e.getKey() + " " + e.getValue());
		}
		
		students.remove(studentNums.get(2), firstNames.get(2)); // will remove the third entry
		
		// user input for new student number
		System.out.print("Enter your student number: ");
		studentNumber = input.next();
		
		while(!studentNumChecker(studentNumber)) {
			System.out.print("Invalid student number! Please input a valid student number: ");
			studentNumber = input.next();
		}
		
		// user input for new student first name
		System.out.print("Enter your first name: ");
		firstName = input.next();
		
		while(!firstNameChecker(firstName)) {
			System.out.print("Invalid first name! Please input a valid first name: ");
			firstName = input.next();
		}
		
		// will insert the new third entry for students map
		students.put(studentNumber, firstName);
		
		System.out.println("Student List:");
		for(Map.Entry e : students.entrySet()) {
			System.out.println(e.getKey() + " " + e.getValue());
		}
	}// end of main method
	
	public static boolean studentNumChecker(String studentNum) {
		return studentNum.matches("[0-9]{4}-[0-9]{4}");
	}// end of studentNumChecker method
	
	public static boolean firstNameChecker(String firstName) {
		return firstName.matches("[a-zA-Z]*");
	}// end of firstNameChecker method
} // end of Main class
