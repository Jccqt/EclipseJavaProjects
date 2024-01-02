package hands_on_activity_08;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class Main {
	
	public static void main(String[]args) {
		Scanner input = new Scanner(System.in);
		Set group1 = new HashSet();
		Set group2 = new HashSet();
		Set self = new HashSet();
		String month;
		
		// Birth month for group 1
		for(int i = 1; i <= 3; i++) {
			System.out.print("Enter birth month " + i + ": ");
			month = input.next();
			
			// will loop if the input user is not a month
			while(!monthChecker(month)) {
				System.out.print("Invalid month! Please enter birth month again: ");
				month = input.next();
			}
			
			// will add all month to group 1
			Collections.addAll(group1, month);
		}
		
		// Birth month for group 2
		for(int i = 1; i <= 3; i++) {
			System.out.print("Enter birth month " + i + ": ");
			month = input.next();
			
			// will loop if the input user is not a month
			while(!monthChecker(month)) {
				System.out.print("Invalid month! Please enter birth month again: ");
				month = input.next();
			}
						
			// will add all month to group 2
			Collections.addAll(group2, month);
		}
		
		System.out.println("Group 1: " + group1);
		System.out.println("Group 2: " + group2);
		
		// Will insert/add user's birth month to self set
		System.out.print("Enter your birth month: ");
		month = input.next();
		
		// will loop if the input user is not a month
		while(!monthChecker(month)) {
			System.out.print("Invalid month! Please enter birth month again: ");
			month = input.next();
		}
		
		self.add(month);
		
		//Objects for set operations
		Set union = new HashSet(group1);
		Set inter = new HashSet(group1);
		Set diff = new HashSet(group1);
		
		union.addAll(group2); // will contain all elements in either set
		inter.retainAll(group2); // will contains only the element common to both set
		diff.removeAll(group2); // will contains elements that are in group 1 but not in group 2
		
		System.out.println("Union: " + union);
		System.out.println("Intersection: " + inter);
		System.out.println("Difference: " + diff);
		
		if(group1.containsAll(self) && group2.containsAll(self)) {
			System.out.println("You have same birth month with two of your classmates in group 1 and group 2.");
		}else if(group1.containsAll(self)){
			System.out.println("You have same birth month with one of your classmate in group 1.");
		}else if(group2.containsAll(self)){
			System.out.println("You have same birth month with one of your classmate in group 2.");
		}else {
			System.out.println("You doesn't have same birth month with your classmates in group 1 and group 2.");
		}
	}// end of main method
	
	public static boolean monthChecker(String month) {
		return month.matches("(j|J)anuary|(f|F)ebruary|(m|M)arch|(a|A)pril|(m|M)ay|(j|J)une|(j|J)uly"
				+ "|(a|A)ugust|(s|S)eptember|(o|O)ctober|(n|N)ovember|(d|D)ecember");
	}// end of monthChecker method
}// end of Main class
