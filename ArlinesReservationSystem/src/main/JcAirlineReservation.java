package main;

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class JcAirlineReservation {
	
	static Scanner in = new Scanner(System.in);
	static Random rand = new Random();
	
	static String avail, menu, bookingNum, fName, lName, rowPick, changeSeat;
	static int eco = 44, ePlus = 6, typePicker, colPick, ePlusRandR, ePlusRandC, arrayCol, ecoRandR, ecoRandC;
	static final int rows = 3, columns = 18;
	
	static char [][] seats = new char[rows][columns];
	static int [] randRow = {0, 1, 2};
	static int [] ePlusRandCol = {0, 1, 2, 11};
	static int [] ecoRandCol = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 17};
	static int [] listCol = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 18, 19, 20, 21, 22, 23, 24};
	static char [] listRow = {'D', 'C', 'A'};
	
	public static void main(String[]args) {
	
		Arrays.fill(seats[0], 'A'); // Set 'A' to all elements in row 1
		Arrays.fill(seats[1], 'A'); // Set 'A' to all elements in row 2
		Arrays.fill(seats[2], 'A'); // Set 'A' to all elements in row 3
		
		printSeats(); // Print seats
		
		while(ePlus > 0 || eco > 0) {
			if(ePlus > 0 && eco > 0) {
				System.out.print("\nDo you want to avail seat? Yes[Y] / No[N] : ");
				avail = in.next();
				if(avail.equalsIgnoreCase("Y")) {
					System.out.println("\nReservation System Menu"
						+"\n[1] Economy Plus"
						+"\n[2] Economy");
				
					System.out.print("Enter chosen menu: ");
					menu = in.next();
					
					if(menu.equalsIgnoreCase("1")) {
						economyPlus();
					}else if(menu.equalsIgnoreCase("2")) {
						economy();
					}	
				}else if(avail.equalsIgnoreCase("N")) {
					System.out.println("Thank you! Next flight leaves in 3 hours.");
					System.exit(0); // will terminate the program
				}	
			}else if(ePlus == 0) {
				System.out.println("ECONOMY PLUS seats are FULL!"
						+"\nChange to ECONOMY seating? [Y]Yes / [N]No");
				changeSeat = in.next();
				
				if(changeSeat.equalsIgnoreCase("Y")) {
					economy();
				}else if(changeSeat.equalsIgnoreCase("N")) {
					System.out.println("Thankyou! Next flight leaves in 3 hours!");
					System.exit(0); // will terminate the program
				}
			}else if(eco == 0) {
				System.out.println("ECONOMY seats are FULL!"
						+"\nChange to ECONOMY PLUS seating? [Y]Yes / [N]No");
				changeSeat = in.next();
				
				if(changeSeat.equalsIgnoreCase("Y")) {
					economyPlus();
				}else if(changeSeat.equalsIgnoreCase("N")) {
					System.out.println("Thankyou! Next flight leaves in 3 hours!");
					System.exit(0); // will terminate the program
				}
			}
		}
		if(ePlus == 0 && eco == 0) {
			System.out.println("All passengers are on-board!"
					+"\nThank you! Next flight leaves in 3 hours.");
			System.exit(0); // will terminate the program
		}
	} // end of main method
	
	public static void economyPlus() {
		menu = "Economy Plus"; // the value of menu will changed into type of seat
		passDetails(); // will ask user's details
		ePlusPicker(); // will choose seat (Manual or Random) (economy plus)
		
	} // end of economyPlus method
	
	public static void ePlusPicker() {
		System.out.println("****************************************************************************"
				+"\n"
				+"\nEconomy Plus Seat Assignment"
				+"\n[1] Random Pick"
				+"\n[2] Manual Pick");

		System.out.print("Enter Chosen Menu: ");
		typePicker = in.nextInt();
		
		if(typePicker == 1) {
			ePlusRand(); // will generate random number for seat row and seat column
			
			while(seats[ePlusRandR][ePlusRandC] == 'O') {
				ePlusRand();
				if(seats[ePlusRandR][ePlusRandC] != 'O') {
					break;
			}
				}
				if(seats[ePlusRandR][ePlusRandC] == 'A') {
					ePlus--;
					seats[ePlusRandR][ePlusRandC] = 'O';
					ePlusBoardingPassRand();
					printSeats();
				}
		}else if(typePicker == 2) {
			System.out.println("----------- Manual Pick-------------");
			System.out.print("\nEnter Row [A, C, D] : ");
			rowPick = in.next().toUpperCase();
			
			switch(rowPick) {
			
			case "A" :
				System.out.print("Enter Column [1, 18] : ");
				colPick = in.nextInt();
				
				while(colPick != 1 && colPick != 18) {
					System.out.print("\nInvalid Seat! Please enter a valid column [1, 18] :");
					colPick = in.nextInt();
					
					if(colPick == 1 || colPick == 18) {
						break;
					}
				}
				arrayCol = Arrays.binarySearch(listCol, colPick);
				
				if(seats[2][arrayCol] == 'A') {
					seats[2][arrayCol] = 'O';
					ePlus--;
					boardingPassManual();
					printSeats();
				}else {
					System.out.println("The seat was already occupied");
				}
				break;
				
			case "C" :
				System.out.print("Enter Column [3, 18] : ");
				colPick = in.nextInt();
				
				while(colPick != 3 && colPick != 18) {
					System.out.print("\nInvalid Seat! Please enter a valid column [3, 18] : ");
					colPick = in.nextInt();
					
					if(colPick == 3 || colPick == 18) {
						break;
					}
				}
				arrayCol = Arrays.binarySearch(listCol, colPick);
				
				if(seats[1][arrayCol] == 'A') {
					seats[1][arrayCol] = 'O';
					ePlus--;
					boardingPassManual();
					printSeats();
				}else {
					System.out.println("The seat was already occupied");
				}
				break;
				
			case "D" :
				System.out.print("Enter Column [3, 18] : ");
				colPick = in.nextInt();
				
				while(colPick != 3 && colPick != 18) {
					System.out.print("\nInvalid Seat! Please enter a valid column [3, 18] : ");
					colPick = in.nextInt();
					
					if(colPick == 3 || colPick == 18) {
						break;
					}
				}
				arrayCol = Arrays.binarySearch(listCol, colPick);
						
				if(seats[0][arrayCol] == 'A') {
					seats[0][arrayCol] = 'O';
					ePlus--;
					boardingPassManual();
					printSeats();
				}else {
					System.out.println("The seat was already occupied");
				}
				break;
				
				default : System.out.println("Invalid Seat!");
			}	
		}
	}// end of ePlusPicker method
	
	public static void ePlusRand() {
		ePlusRandR = randRow[rand.nextInt(randRow.length)]; // Random Row (A, C, D)
		ePlusRandC = ePlusRandCol[rand.nextInt(ePlusRandCol.length)]; // Random Column (1, 3, 18)
		
		if((ePlusRandR == 0 && ePlusRandC == 0) || (ePlusRandR == 0 && ePlusRandC == 1) || (ePlusRandR == 1 && ePlusRandC == 0) || (ePlusRandR == 1 && ePlusRandC == 1) || (ePlusRandR == 2 && ePlusRandC == 1) || (ePlusRandR == 2 && ePlusRandC == 2)) {
			while((ePlusRandR == 0 && ePlusRandC == 0) || (ePlusRandR == 0 && ePlusRandC == 1) || (ePlusRandR == 1 && ePlusRandC == 0) || (ePlusRandR == 1 && ePlusRandC == 1) || (ePlusRandR == 2 && ePlusRandC == 1) || (ePlusRandR == 2 && ePlusRandC == 2)) {
				ePlusRandR = randRow[rand.nextInt(randRow.length)];
				ePlusRandC = ePlusRandCol[rand.nextInt(ePlusRandCol.length)];
				
				if((ePlusRandR == 0 && ePlusRandC == 0) || (ePlusRandR == 0 && ePlusRandC == 1) || (ePlusRandR == 1 && ePlusRandC == 0) || (ePlusRandR == 1 && ePlusRandC == 1) || (ePlusRandR == 2 && ePlusRandC == 1) || (ePlusRandR == 2 && ePlusRandC == 2) ||(seats[ePlusRandR][ePlusRandC] == 'O')) {
					continue;
				} else {
					break;
				}
			}
		}
		
	} // end of ePlusRand method
	
	public static void economy() {
		menu = "Economy"; // the value of menu will changed into type of seat
		passDetails(); // will ask user's details
		ecoPicker(); // will choose seat (Manual or Random) (economy plus)
	}// end of economy method
	
	public static void ecoPicker() {
		System.out.println("****************************************************************************"
				+"\n"
				+"\nEconomy Plus Seat Assignment"
				+"\n[1] Random Pick"
				+"\n[2] Manual Pick");
		
		System.out.print("Enter Chosen Menu: ");
		typePicker = in.nextInt();
		
		if(typePicker == 1) {
			ecoRand();
			
			while(seats[ecoRandR][ecoRandC] == 'O') {
				ecoRand();
				if(seats[ecoRandR][ecoRandC] != 'O') {
					break;
				}
			}
			if(seats[ecoRandR][ecoRandC] == 'A') {
				eco--;
				seats[ecoRandR][ecoRandC] = 'O';
				ecoBoardingPassRand();
				printSeats();
			}
		}else if(typePicker == 2) {
			System.out.println("----------- Manual Pick-------------");
			System.out.print("\nEnter Row [A, C, D] : ");
			rowPick = in.next().toUpperCase();
			
			switch(rowPick) {
			
			case "A" :
				System.out.print("Enter Column [2-11 and 19-24] : ");
				colPick = in.nextInt();
				
				while(colPick != 2 && colPick != 3 && colPick != 4 && colPick != 5 && colPick != 6 && colPick != 7 && colPick != 8 && colPick != 9 && colPick != 10 && colPick != 11 && colPick != 19 && colPick != 20 && colPick != 21 && colPick != 22 && colPick != 23 && colPick != 24) {
					System.out.print("Invalid Seat! Please enter a valid column [2-11 and 19-24]");
					colPick = in.nextInt();
					if(colPick == 2 || colPick == 3 || colPick == 4 || colPick == 5 || colPick == 6 || colPick == 7 || colPick == 8 || colPick == 9 || colPick == 10 || colPick == 11 || colPick == 19 || colPick == 20 || colPick == 21 || colPick == 22 || colPick == 23 || colPick == 24) {
						break;
					}
				}
				arrayCol = Arrays.binarySearch(listCol, colPick);
				
				if(seats[2][arrayCol] == 'A') {
					seats[2][arrayCol] = 'O';
					eco--;
					boardingPassManual();
					printSeats();
				}else {
					System.out.println("The seat was already occupied");
				}
				break;
			
			case "C" :
				System.out.print("Enter Column [4-11 and 19-24] : ");
				colPick = in.nextInt();
				
				while(colPick != 4 && colPick != 5 && colPick != 6 && colPick != 7 && colPick != 8 && colPick != 9 && colPick != 10 && colPick != 11 && colPick != 19 && colPick != 20 && colPick != 21 && colPick != 22 && colPick != 23 && colPick != 24) {
					System.out.print("Invalid Seat! Please enter a valid column [4-11 and 19-24] : ");
					colPick = in.nextInt();
					if(colPick == 4 && colPick == 5 && colPick == 6 && colPick == 7 && colPick == 8 && colPick == 9 && colPick == 10 && colPick == 11 && colPick == 19 && colPick == 20 && colPick == 21 && colPick == 22 && colPick == 23 && colPick == 24) {
						break;
					}
				}
				arrayCol = Arrays.binarySearch(listCol, colPick);
				
				if(seats[1][arrayCol] == 'A') {
					seats[1][arrayCol] = 'O';
					eco--;
					boardingPassManual();
					printSeats();
				}else {
					System.out.println("The seat was already occupied");
				}
				break;
				
			case "D" :
				System.out.print("Enter Column [4-11 and 19-24] : ");
				colPick = in.nextInt();
				
				while(colPick != 4 && colPick != 5 && colPick != 6 && colPick != 7 && colPick != 8 && colPick != 9 && colPick != 10 && colPick != 11 && colPick != 19 && colPick != 20 && colPick != 21 && colPick != 22 && colPick != 23 && colPick != 24) {
					System.out.print("Invalid Seat! Please enter a valid column [4-11 and 19-24] : ");
					colPick = in.nextInt();
					if(colPick == 4 && colPick == 5 && colPick == 6 && colPick == 7 && colPick == 8 && colPick == 9 && colPick == 10 && colPick == 11 && colPick == 19 && colPick == 20 && colPick == 21 && colPick == 22 && colPick == 23 && colPick == 24) {
						break;
					}
				}
				arrayCol = Arrays.binarySearch(listCol, colPick);
				
				if(seats[0][arrayCol] == 'A') {
					seats[0][arrayCol] = 'O';
					eco--;
					boardingPassManual();
					printSeats();
				}else {
					System.out.println("The seat was already occupied");
				}
				break;
				
				default : System.out.println("Invalid Seat!");
			}
		}
	}// end of ecoPicker method
	
	public static void ecoRand() {
		ecoRandR = randRow[rand.nextInt(randRow.length)]; // Random Row (A, C, D)
		ecoRandC = ecoRandCol[rand.nextInt(ecoRandCol.length)]; // Random Column (2-11 and 19-24)
		
		if((ecoRandR == 0 && ecoRandC == 1) || (ecoRandR == 1 && ecoRandC == 1) || (ecoRandR == 0 && ecoRandC == 2) || (ecoRandR == 1 && ecoRandC == 2)) {
			while((ecoRandR == 0 && ecoRandC == 1) || (ecoRandR == 1 && ecoRandC == 1) || (ecoRandR == 0 && ecoRandC == 2) || (ecoRandR == 1 && ecoRandC == 2)) {
				ecoRandR = randRow[rand.nextInt(randRow.length)];
				ecoRandC = ecoRandCol[rand.nextInt(ecoRandCol.length)];
				
				if((ecoRandR == 0 && ecoRandC == 1) || (ecoRandR == 1 && ecoRandC == 1) || (ecoRandR == 0 && ecoRandC == 2) || (ecoRandR == 1 && ecoRandC == 2)) {
					continue;
				} else {
					break;
				}
			}
		}
	}// end of ecoRand method
	
	public static void passDetails() {
		System.out.println("*************************ENTER PASSENGER DETAILS****************************"
				+"\nSEAT TYPE : " + menu.toUpperCase()
				+"\n");
		
		System.out.print("Enter Booking Number : ");
		bookingNum = in.next();
		
		System.out.print("Enter Passengers Last Name : ");
		lName = in.next();
		
		System.out.print("Enter Passengers First Name : ");
		fName = in.next();
	} // end of passDetails method
	
	public static void boardingPassManual() {
		System.out.println("--------------------------------BOARDING PASS-------------------------------"
				+"\n"
				+"\nSEAT TYPE : " + menu.toUpperCase()
				+"\nSEAT NUMBER : " + rowPick.toUpperCase() + colPick
				+"\nBooking Number : " + bookingNum.toUpperCase()
				+"\nPassenger's Last Name : " + lName.toUpperCase()
				+"\nPassenger's First Name : " + fName.toUpperCase()
				+"\n----------------------------------------------------------------------------\n");
	}// end of boardingPassManual method
	
	public static void ePlusBoardingPassRand() {
		System.out.println("--------------------------------BOARDING PASS-------------------------------"
				+"\n"
				+"\nSEAT TYPE : " + menu.toUpperCase()
				+"\nSEAT NUMBER : " + listRow[ePlusRandR] + listCol[ePlusRandC]
				+"\nBooking Number : " + bookingNum.toUpperCase()
				+"\nPassenger's Last Name : " + lName.toUpperCase()
				+"\nPassenger's First Name : " + fName.toUpperCase()
				+"\n----------------------------------------------------------------------------\n");
	}// end of ePlusBoardingPassRand method
	
	public static void ecoBoardingPassRand() {
		System.out.println("--------------------------------BOARDING PASS-------------------------------"
				+"\n"
				+"\nSEAT TYPE : " + menu.toUpperCase()
				+"\nSEAT NUMBER : " + listRow[ecoRandR] + listCol[ecoRandC] 
				+"\nBooking Number : " + bookingNum.toUpperCase()
				+"\nPassenger's Last Name : " + lName.toUpperCase()
				+"\nPassenger's First Name : " + fName.toUpperCase()
				+"\n----------------------------------------------------------------------------\n");	
	} // end of ecoBoardingPassRand method
	
	public static void printSeats() {
		System.out.println("*************************AIRLINE RESERVATION SYSTEM************************"
				+"\n        Seats Availability : Economy Plus = " + ePlus + " Economy = " + eco
				+"\n"
				+"\n       1    2    3    4    5    6    7    8    9    10   11   18   19   20   21   22   23   24"
				+"\n--------------------------------------------------------------------------------------------------");
		
		for(int a = 0; a < rows; a++) {
			if(a == 0) {
				System.out.print("  D |  ");
			}else if(a == 1) {
				System.out.print("  C |  ");
			}else if(a == 2) {
				System.out.print("  A |  ");
			}
			for(int b = 0; b < columns; b++) {
				seats[0][0] = 'N';
				seats[0][1] = 'N';
				seats[1][0] = 'N';
				seats[1][1] = 'N';
				
				System.out.print(seats[a][b] + "    ");
			}
			if(a == 0) {
				System.out.print("| D");
			}else if(a == 1) {
				System.out.print("| C");
				System.out.println("");
			}else if(a == 2) {
				System.out.print("| A");
			}
			System.out.println(" ");
		}
		System.out.println("--------------------------------------------------------------------------------------------------"
				+"\n"
				+"\n Legend: A = Available  O = Occupied  N = Not Applicable"
				+"\n*******************************************************************");
	} // end of printSeats method
} // end of class