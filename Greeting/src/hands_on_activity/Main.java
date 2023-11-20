package hands_on_activity;

import java.util.Scanner;
import java.util.PriorityQueue;

public class Main {
	
	public static void main(String[]args) {
		PriorityQueue<String> nicknames = new PriorityQueue<>();
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter the number of nicknames you want to enter [LIMIT: 10]: ");
		String nicknameNum = input.next();
		
		int nicknameNums = Integer.parseInt(nicknameNum);
		
		// will loop if the user input is alphabetical or the number is outside the limit(10)
		while(!nicknameNumChecker(nicknameNum) || nicknameNums > 10) {
			if(!nicknameNumChecker(nicknameNum)) {
			System.out.print("Invalid input! Please enter numerical only [0-10]: ");
			nicknameNum = input.next();
			nicknameNums = Integer.parseInt(nicknameNum);
			}else if(nicknameNums > 10) {
				System.out.print("Input was outside the limit! Please enter a valid number [LIMIT: 10]: ");
				nicknameNum = input.next();
				nicknameNums = Integer.parseInt(nicknameNum);
			}
		}
		
		System.out.println("Enter the nicknames of " + nicknameNum + " of your classmates: " );
		
		input.nextLine(); // will not consume new line when looping
		
		// will loop based on the numbers from user input
		for(int i = 0; i < nicknameNums; i++) {
			String classmateNickname = input.nextLine();
			nicknames.add(classmateNickname);
		}
		
		while(true) {
			System.out.print("\nPress 'H' to say Hi to each of them: ");
			String sayHi = input.next();
			
			while(!sayHiChecker(sayHi)) {
				System.out.print("\nInvalid input! Please enter again: ");
				sayHi = input.next();
			}
			if(!nicknames.isEmpty()) {
				System.out.println("\nHi " + nicknames.remove() + "!"); // will print nicknames in sorted order
			}else {
				System.out.println("\nDone sayng Hi.");
				System.exit(0); // will terminate the program
			}
		}
	}// end of main method
	
	public static boolean nicknameNumChecker(String nicknameNum) {
		return nicknameNum.matches("[0-9]*");
	}// end of nicknameNumChecker method
	
	public static boolean sayHiChecker(String sayHi) {
		return sayHi.matches("[h|H]");
	}// end of sayHiChecker method
}// end of Main class
