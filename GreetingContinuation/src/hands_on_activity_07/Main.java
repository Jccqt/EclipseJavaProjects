package hands_on_activity_07;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	
	static Scanner input = new Scanner(System.in);
	static PriorityQueue nicknames = new PriorityQueue();
	
	public static void main(String[]args) {
	
		
		askNicknames(); // will ask user inputs for nicknames and will add it to queue
		sayHi(); // will say Hi to the nicknames on queue
		
		while(true) {
			System.out.println("Press the first letter of your firstname to continue,"
					+ " kindly use second letter if its X, x to end the program");
			String userChoice = input.next();
		
			if(userChoice.equalsIgnoreCase("X")) {
				System.out.println("PROGRAM ENDED");
				System.exit(0); // will terminate the program
			}else {
				askNicknames(); // will ask user inputs for nicknames and will add it to queue
				sayHi(); // will say Hi to the nicknames on queue
			}
		}
	}// end of main method
	
	public static void askNicknames() {
		System.out.print("Enter number of inputs: ");
		String nicknameNum = input.next();
		
		while(!nicknameNumChecker(nicknameNum)) {
			System.out.print("Invalid input! Please enter numerical only: ");
			nicknameNum = input.next();
		}
		int nicknameNums = Integer.parseInt(nicknameNum); // will convert String into Integer
		
		System.out.println("Enter " + nicknameNums + " names:");
		
		//will add user inputs to queue based on numbers of nicknames
		while(true) {
			nicknames.add(input.next());
			
			if(nicknames.size() == nicknameNums) {
				break;
			}
		}
		
		// will print nicknames
		System.out.println(nicknames);
		
	}// end of askNicknames method
	
	public static void sayHi() {
		System.out.println("Press H to say Hi to each of them");
		
		while(true) {
			String sayHi = input.next();
				
			if(sayHi.equalsIgnoreCase("H")) {
				System.out.println("Hi " + nicknames.remove() + "!");
			}else {
				System.out.println("Invalid input");
			}
			if(nicknames.isEmpty()) {
				System.out.println("Done saying Hi");
				break;
			}
		}
	}// end of sayHi method
	
	public static boolean nicknameNumChecker(String nicknameNum) {
		return nicknameNum.matches("[0-9]*");
	}// end of nicknameNumChecker method
}// end of Main class
