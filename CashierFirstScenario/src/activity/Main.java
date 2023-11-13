package activity;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.lang.Thread;


public class Main {
	
	//Global Objects
	static Stack<String> cashier1 = new Stack<>();
	static Stack<String> cashier2 = new Stack<>();
	static Stack<String> cashier3 = new Stack<>();
	static Stack<String> cashier4 = new Stack<>();
	static Stack<String> cashier5 = new Stack<>();
	static Queue<String> customers = new LinkedList<>();
	static Stack<String> customers1 = new Stack<>();
	static Stack<String> customers2 = new Stack<>();
	static Stack<String> customers3 = new Stack<>();
	static Stack<String> customers4 = new Stack<>();
	static Stack<String> customers5 = new Stack<>();
	static Queue<String> customersReverse1 = new LinkedList<>();
	static Queue<String> customersReverse2 = new LinkedList<>();
	static Queue<String> customersReverse3 = new LinkedList<>();
	static Queue<String> customersReverse4 = new LinkedList<>();
	static Queue<String> customersReverse5 = new LinkedList<>();
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[]args) {
		
		for(int i = 0; i < 20; i++) {
			System.out.print("Enter a customer number: ");
			String customerNum = input.next();
			
			//will loop while the user input is not numerical
			while(!customerChecker(customerNum)) {
				System.out.print("Invalid input! Please input numbers only: ");
				customerNum = input.next();
			}
			
			//will add customers to queue
			customers.offer("Customer " + customerNum);
			
			//will push customers to stack
			if(cashier1.size() < 4) {
				cashier1.push(customers.poll());
			}else if(cashier2.size() < 4) {
				cashier2.push(customers.poll());
			}else if(cashier3.size() < 4) {
				cashier3.push(customers.poll());
			}else if(cashier4.size() < 4) {
				cashier4.push(customers.poll());
			}else if(cashier5.size() < 4) {
				cashier5.push(customers.poll());
			}
			
			// will show the added item to the stack
			System.out.println("Cashier 1 : " + cashier1);
			System.out.println("Cashier 2 : " + cashier2);
			System.out.println("Cashier 3 : " + cashier3);
			System.out.println("Cashier 4 : " + cashier4);
			System.out.println("Cashier 5 : " + cashier5);
		}
		
		manualReverse(); // will reverse/sort the items manually
		
		System.out.println("\nCustomers queue is now full! Will start to accomodate customers now.");
		int cashierCounter = 1;
		try {
			for(int i = 0; i < 4; i++) {
			Thread.sleep(1000); //will sleep for 1 second
			System.out.print("\nProcessing");
			for(int k = 0; k < 3; k++) {
				Thread.sleep(500); // will sleep for 0.5 second
				System.out.print(".");
			}
			if(customersReverse1.size() <= 4) {
				customersReverse1.poll();
			}
			if(customersReverse2.size() <= 4) {
				customersReverse2.poll();
			}
			if(customersReverse3.size() <= 4) {
				customersReverse3.poll();
			}
			if(customersReverse4.size() <= 4) {
				customersReverse4.poll();
			}
			if(customersReverse5.size() <= 4) {
				customersReverse5.poll();
			}
			System.out.println("\nCashier 1 : " + customersReverse1 + " (Number of accomodated customers: " + cashierCounter + ")");
			System.out.println("Cashier 2 : " + customersReverse2 + " (Number of accomodated customers: " + cashierCounter + ")");
			System.out.println("Cashier 3 : " + customersReverse3 + " (Number of accomodated customers: " + cashierCounter + ")");
			System.out.println("Cashier 4 : " + customersReverse4 + " (Number of accomodated customers: " + cashierCounter + ")");
			System.out.println("Cashier 5 : " + customersReverse5 + " (Number of accomodated customers: " + cashierCounter + ")");
			cashierCounter++;
			}
		}catch(Exception e) {
			System.out.println(e);
		
		}	
		
	}// end of main method
	
	public static void manualReverse() {
		for(int i = 0; i < 4; i ++) {
			customers1.push(cashier1.pop());
			customers2.push(cashier2.pop());
			customers3.push(cashier3.pop());
			customers4.push(cashier4.pop());
			customers5.push(cashier5.pop());
		}
		for(int i = 0; i < 4; i++) {
			customersReverse1.offer(customers1.pop());
			customersReverse2.offer(customers2.pop());
			customersReverse3.offer(customers3.pop());
			customersReverse4.offer(customers4.pop());
			customersReverse5.offer(customers5.pop());
		}
	}// end of manualReverse method
	
	public static boolean customerChecker(String customerNum) {
		return customerNum.matches("[0-9]*");
	}// end of customerChecker
	
}// end of Main class
