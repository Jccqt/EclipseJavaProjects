package task_performance;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.PriorityQueue;

public class SJF {
	
	static Scanner input = new Scanner(System.in);
	static int processNum, arrivalTime, burstTime, waitingTime, ganttTimeCounter, turnAroundTime;
	static double TRTS, averageTAT, averageWT, averageTRTS;
	static String format = "%7s";
	static String ganttFormat = "%8s";
	static String formatDouble = "%.2f";
	static boolean processLoop;
	
	static List<Integer> processID = new LinkedList<>();
	static List<Integer> runningProcess = new LinkedList<>();
	static List<Integer> burst = new LinkedList<>();
	static List<Integer> finishedProcess = new LinkedList<>();
	static List<Integer> ganttProcess = new LinkedList<>();
	static List<Integer> ganttTime = new LinkedList<>();
	static PriorityQueue<Integer> queue = new PriorityQueue<>();
	static Map<Integer,Integer> processID_arrivalTime = new HashMap<>();
	static Map<Integer,Integer> burstTime_processID = new HashMap<>();
	static Map<Integer,Integer> processID_burstTime = new HashMap<>();
	static Map<Integer,Integer> processID_burstTable = new HashMap<>();
	static Map<Integer,Integer> processID_finishTime = new HashMap<>();
	static Map<Integer,Integer> processID_turnAroundTime = new HashMap<>();
	static Map<Integer,Integer> processID_waitingTime = new HashMap<>();
	static Map<Integer,Double> processID_TRTS = new HashMap<>();
	
	public static void main(String[]args) {
		
		while(true) {
			System.out.print("Press [A] to add process, [X] to exit: ");
			String userChoice = input.next();
			
			if(userChoice.equalsIgnoreCase("A")) {
				processID.clear();
				runningProcess.clear();
				burst.clear();
				finishedProcess.clear();
				ganttProcess.clear();
				ganttTime.clear();
				processID_arrivalTime.clear();
				burstTime_processID.clear();
				processID_burstTime.clear();
				processID_burstTable.clear();
				processID_finishTime.clear();
				processID_turnAroundTime.clear();
				processID_waitingTime.clear();
				processID_TRTS.clear();
				System.out.print("Enter number of process: ");
				processNum = input.nextInt();
				
				for(int i = 0; i < processNum; i++) {
					int counter = 1 + i;
					
					System.out.print("Enter process " + counter + " arrival time: ");
					arrivalTime = input.nextInt();
					
					System.out.print("Enter process " + counter + " burst time: ");
					burstTime = input.nextInt();
					
					processID.add(counter);
					burst.add(burstTime);
					processID_arrivalTime.put(processID.get(i), arrivalTime);
					burstTime_processID.put(burstTime, processID.get(i));
					processID_burstTime.put(processID.get(i), burstTime);
					processID_burstTable.put(processID.get(i), burstTime);
				}
				
				
				System.out.println("");
				System.out.print("Process         |");
				for(int i = 0; i < processID.size(); i++) {
					System.out.printf(format, processID.get(i));
					System.out.print("|");
				}
						
				System.out.println("");
				System.out.print("Arrival Time    |");
				for(int i = 0; i < processID.size(); i++) {
					System.out.printf(format, processID_arrivalTime.get(processID.get(i)));
					System.out.print("|");
				}		
			
				System.out.println("");
				System.out.print("Burst Time      |");
				for(int i = 0; i < processID.size(); i++) {
					System.out.printf(format, processID_burstTime.get(processID.get(i)));
					System.out.print("|");
				}
				
				processLoop = true;
				ganttTimeCounter = 0;
				
				queue.offer(burst.get(0));
				while(processLoop) {
					runningProcess.add(burstTime_processID.get(queue.poll()));
					
					while(processID_burstTime.get(runningProcess.get(0)) != 0) {
						ganttTimeCounter++;
						
						for(int i = 0; i < processID.size(); i++) {
							arrivalTime = processID_arrivalTime.get(processID.get(i));
							if(arrivalTime == ganttTimeCounter) {
								queue.offer(burst.get(i));
							}
						}
						
						burstTime = processID_burstTime.get(runningProcess.get(0));
						if(burstTime != 0) {
							burstTime -= 1;
							processID_burstTime.replace(runningProcess.get(0), burstTime);
						}
						if(burstTime == 0) {
							finishedProcess.add(runningProcess.get(0));
							processID_finishTime.put(runningProcess.get(0), ganttTimeCounter);
						}
					}
					
					ganttProcess.add(runningProcess.get(0));
					ganttTime.add(ganttTimeCounter);
					
					runningProcess.clear();
					if(finishedProcess.size() == processID.size()) {
						processLoop = false;
					}
				}
				
				System.out.println("");
				System.out.print("Finish Time     |");
				for(int i = 0; i < processID.size(); i++) {
					System.out.printf(format, processID_finishTime.get(processID.get(i)));
					System.out.print("|");
				}
				
				System.out.println("");
				System.out.print("Turnaround Time |");
				for(int i = 0; i < processID.size(); i++) {
					turnAroundTime = processID_finishTime.get(processID.get(i)) - processID_arrivalTime.get(processID.get(i));
					processID_turnAroundTime.put(processID.get(i), turnAroundTime);
					System.out.printf(format, processID_turnAroundTime.get(processID.get(i)));
					System.out.print("|");
				}
				
				System.out.println("");
				System.out.print("Waiting Time    |");
				for(int i = 0; i < processID.size(); i++) {
					waitingTime = processID_turnAroundTime.get(processID.get(i)) - processID_burstTable.get(processID.get(i));
					processID_waitingTime.put(processID.get(i), waitingTime);
					System.out.printf(format, processID_waitingTime.get(processID.get(i)));
					System.out.print("|");
				}
				
				System.out.println("");
				System.out.print("Tr/Ts           |");
				for(int i = 0; i < processID.size(); i++) {
					TRTS = processID_turnAroundTime.get(processID.get(i)) / (double)processID_burstTable.get(processID.get(i));
					processID_TRTS.put(processID.get(i), TRTS);
					System.out.printf(formatDouble, processID_TRTS.get(processID.get(i)));
					System.out.print("|");
				}
				
				System.out.println("\n");
				System.out.println("Gantt Chart: ");
				for(int i = 0; i < ganttProcess.size(); i++) {
					System.out.print("---------");
				}
				System.out.print("\n|");
				for(int i = 0; i < ganttProcess.size(); i++) {
					System.out.printf(ganttFormat, ganttProcess.get(i));
					System.out.print("|");
				}
				System.out.println("");
				for(int i = 0; i < ganttProcess.size(); i++) {
					System.out.print("---------");
				}
				
				System.out.println("");
				System.out.print(0);
				
				for(int i = 0; i < ganttTime.size(); i++) {
					System.out.printf(ganttFormat, ganttTime.get(i));
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
				
				averageTAT = turnAroundTime / (double)processNum; 
				averageWT = waitingTime / (double)processNum; 
				averageTRTS = TRTS / (double)processNum; 
				
				System.out.println("Average Turnaround Time: " + turnAroundTime + " / " + processNum + " = " + averageTAT);
				System.out.println("Average Waiting Time: " + waitingTime + " / " + processNum + " = " + averageWT);
				System.out.printf("Average Tr/Ts: " + TRTS + " / " + processNum + " = %.2f",  averageTRTS);
			}else if(userChoice.equalsIgnoreCase("X")){
				System.exit(0);
			}else {
				System.out.println("Invalid choice!");
			}
		}
	}// end of main method
}// end of SJF class	
