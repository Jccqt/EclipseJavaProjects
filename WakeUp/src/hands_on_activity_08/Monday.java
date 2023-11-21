package hands_on_activity_08;

import java.time.LocalTime;
import java.util.Scanner;

public class Monday extends Weekday{

	static LocalTime alarm;
	
	public static void main(String[]args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter time for alarm in this format (HH:MM) : ");
		String time = input.next();
		
		setAlarm(time);
		showAlarm();
	
		while(true) {
			System.out.print("Do you want to enter another alarm? [Y] Yes / [N] No: ");
			String userChoice = input.next().toUpperCase();
		
			switch(userChoice) {
			case "Y":
				System.out.print("Enter time for alarm in this format (HH:MM) : ");
				time = input.next();
			
				setAlarm(time);
				showAlarm();
				break;
		
			case "N":
				System.out.println("Done setting alarm!");
				System.exit(0); // will terminate the program
				break;
			
				default: System.out.println("Invalid input!");
		}
		}
	}// end of main method

	public static void showAlarm() {
		LocalTime now = LocalTime.now();
		
		if(now.isBefore(alarm)) {
			System.out.println("I'll wake you up later! UwU");
		}else {
			System.out.println("Alarm is set for tomorrow! UwU");
		}
	}// end of showAlarm method
	
	public static void setAlarm(String time) {
		alarm = LocalTime.parse(time); // will convert String into time
	}// end of setAlarm method
	
}// end of Monday class
