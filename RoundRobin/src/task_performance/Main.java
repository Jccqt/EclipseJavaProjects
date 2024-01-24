package task_performance;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Main {
	
	static Scanner input = new Scanner(System.in);
	static int processNum, arrivalTime, burstTime, finishTime,
	turnAroundTime, waitingTime, quantum, timeCounter;
	static double averageTurnAroundTime, averageWaitingTime, averageTRTS, TRTS;
	static boolean processRunning;
	static String tableFormat = "%7s";
	static String ganttFormat = "%8s";
	
	static List<String> processID = new LinkedList<>();
	static List<String> currentProcess = new LinkedList<>();
	static List<String> finishProcess = new LinkedList<>();
	static List<String> ganttP = new LinkedList<>();
	static List<Integer> ganttT = new LinkedList<>();
	static Map<String,Integer> processID_arrivalTime = new HashMap<>();
	static Map<String,Integer> processID_burstTime = new HashMap<>();
	static Map<String,Integer> processID_burstTimeTable = new HashMap<>();
	static Map<String,Integer> processID_finishTime = new HashMap<>();
	static Map<String,Integer> processID_turnAroundTime = new HashMap<>();
	static Map<String,Integer> processID_waitingTime = new HashMap<>();
	static Map<String,Double> processID_TRTS = new HashMap<>();
	static Queue<String> processQueue = new LinkedList<>();
	
	public static void main(String[]args) {
		
		while(true) {
			System.out.print("Do you want to enter process details? (Round Robin) [Y] Yes/ [N] No: ");
			String userAnswer = input.next();
			
			if(userAnswer.equalsIgnoreCase("Y")) {
				clearDetails(); // will clear the elements of hashmap and list objects
				setProcessDetails(); // will set the arrival time and burst time of the process
				roundRobin(); // will do the round robin algorithm
				printTable(); // will print the table of process and its details
				printGanttChart();
			}else if(userAnswer.equalsIgnoreCase("N")) {
				System.out.println("Thankyou and have a nice day!");
				System.exit(0); // will terminate the program
			}else {
				System.out.println("Invalid choice!");
			}
		}
		
	}// end of main class
	
	public static void setProcessDetails() {
		System.out.print("Enter quantum value: ");
		quantum = input.nextInt();
		
		System.out.print("Enter number of process: ");
		processNum = input.nextInt();
		
		for(int i = 0; i < processNum; i++) {
			int processCounter = 1 + i;
			
			System.out.print("Enter the arrival time of process " + processCounter + ": ");
			arrivalTime = input.nextInt();
			
			System.out.print("Enter the burst time of process " + processCounter + ": ");
			burstTime = input.nextInt();
			
			processID.add(String.valueOf(processCounter));
			processID_arrivalTime.put(processID.get(i), arrivalTime);
			processID_burstTime.put(processID.get(i), burstTime);
			processID_burstTimeTable.put(processID.get(i), burstTime);
		}
	}// end of setProcessDetails method
	
	public static void clearDetails() {
		processID.clear();
		currentProcess.clear();
		finishProcess.clear();
		ganttP.clear();
		ganttT.clear();
		processID_arrivalTime.clear();
		processID_burstTime.clear();
		processID_burstTimeTable.clear();
		processID_finishTime.clear();
		processID_turnAroundTime.clear();
		processID_waitingTime.clear();
		processID_TRTS.clear();
	}// end of clearDetails method
	
	public static void printTable() {
		System.out.println("\nQuantum = " + quantum);
		
		// Process row
		System.out.println("");
		System.out.print("Process         |");
		for(int i = 0; i < processID.size(); i++) {
			System.out.printf(tableFormat, processID.get(i));
			System.out.print("|");
		}
		
		// Arrival Time row
		System.out.println("");
		System.out.print("Arrival Time    |");
		for(int i = 0; i < processID.size(); i++) {
			System.out.printf(tableFormat, processID_arrivalTime.get(processID.get(i)));
			System.out.print("|");
		}
		
		// Burst Time row
		System.out.println("");
		System.out.print("Burst Time      |");
		for(int i = 0; i < processID.size(); i++) {
			System.out.printf(tableFormat, processID_burstTimeTable.get(processID.get(i)));
			System.out.print("|");
		}
		
		// Finish Time row
		System.out.println("");
		System.out.print("Finish Time     |");
		for(int i = 0; i < processID.size(); i++) {
			System.out.printf(tableFormat, processID_finishTime.get(processID.get(i)));
			System.out.print("|");
		}
		
		// Turnaround Time row
		System.out.println("");
		System.out.print("Turnaround Time |");
		for(int i = 0; i < processID.size(); i++) {
			turnAroundTime = processID_finishTime.get(processID.get(i)) - processID_arrivalTime.get(processID.get(i));
			processID_turnAroundTime.put(processID.get(i), turnAroundTime);
			System.out.printf(tableFormat, processID_turnAroundTime.get(processID.get(i)));
			System.out.print("|");
		}
		
		// Waiting Time row
		System.out.println("");
		System.out.print("Waiting Time    |");
		for(int i = 0; i < processID.size(); i++) {
			waitingTime = processID_turnAroundTime.get(processID.get(i)) - processID_burstTimeTable.get(processID.get(i));
			processID_waitingTime.put(processID.get(i), waitingTime);
			System.out.printf(tableFormat, processID_waitingTime.get(processID.get(i)));
			System.out.print("|");
		}
		
		// Tr/Ts row
		System.out.println("");
		System.out.print("Tr/Ts           |");
		for(int i = 0; i < processID.size(); i++) {
			TRTS = processID_turnAroundTime.get(processID.get(i)) / (double)processID_burstTimeTable.get(processID.get(i));
			processID_TRTS.put(processID.get(i), TRTS);
			System.out.printf(tableFormat, processID_TRTS.get(processID.get(i)));
			System.out.print("|");
		}
	}// end of printTable method
	
	public static void roundRobin() {
		processRunning = true;
		timeCounter = 0;
		
		processQueue.offer(processID.get(0));
		while(processRunning) {
			
			currentProcess.add(processQueue.poll());
			for(int i = 0; i < quantum; i++) {
				timeCounter++;
				
				for(int j = 0; j < processID.size(); j++) {
					arrivalTime = processID_arrivalTime.get(processID.get(j));
					if(arrivalTime == timeCounter) {
						processQueue.offer(processID.get(j));
					}
				}
				
				burstTime = processID_burstTime.get(currentProcess.get(0));
				if(burstTime != 0) {
					burstTime -= 1;
					processID_burstTime.replace(currentProcess.get(0), burstTime);
				}
				if(burstTime == 0){
					finishProcess.add(currentProcess.get(0));
					processID_finishTime.put(currentProcess.get(0), timeCounter);
					break;
				}
				
			}
			
			ganttP.add(currentProcess.get(0));
			ganttT.add(timeCounter);
			
			if(burstTime != 0) {
				processQueue.offer(currentProcess.get(0));
			}
			
			currentProcess.clear();
			
			if(finishProcess.size() == processID.size()) {
				processRunning = false;
			}
		}
	}// end of roundRobin method
	
	public static void printGanttChart() {
		System.out.println("\n");
		System.out.println("Gantt Chart: ");
		for(int i = 0; i < ganttP.size(); i++) {
			System.out.print("---------");
		}
		System.out.print("\n|");
		for(int i = 0; i < ganttP.size(); i++) {
			System.out.printf(tableFormat, ganttP.get(i));
			System.out.print("|");
		}
		System.out.println("");
		for(int i = 0; i < ganttP.size(); i++) {
			System.out.print("---------");
		}
		
		System.out.println("");
		System.out.print(0);
		
		for(int i = 0; i < ganttT.size(); i++) {
			System.out.printf(ganttFormat, ganttT.get(i));
		}
		
		System.out.println("\n");
		turnAroundTime = 0;
		waitingTime = 0;
		TRTS = 0;
		for(int i = 0; i < processID.size(); i++) {
			turnAroundTime += processID_turnAroundTime.get(processID.get(i));
			waitingTime += processID_waitingTime.get(processID.get(i));
			TRTS += processID_TRTS.get(processID.get(i));
		}
		averageTurnAroundTime = turnAroundTime / (double)processNum; // casting for double data type
		averageWaitingTime = waitingTime / (double)processNum; // casting for double data type
		averageTRTS = TRTS / (double)processNum; // casting for double data type
		
		System.out.println("Average Turnaround Time: " + turnAroundTime + " / " + processNum + " = " + averageTurnAroundTime);
		System.out.println("Average Waiting Time: " + waitingTime + " / " + processNum + " = " + averageWaitingTime);
		System.out.println("Average Tr/Ts: " + TRTS + " / " + processNum + " = " + TRTS);
	}// end of printGanttChart
}// end of Main class
