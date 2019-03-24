package labs;
import java.util.Random;
public class Lab1 {
	

	

	
			private static void waitingTime(int n, int executionTime[], int waitingTime[],int arrivalTime[]) {
				waitingTime[0]=0;
				for(int i=1;i<n;i++) {
					waitingTime[i]=waitingTime[i-1]+executionTime[i-1]-arrivalTime[i];
				}
			}
			private static void averageWaitingTime(int n, int executionTime[],int arrivalTime[]) {
				int totalWaitingTime=0;
				int waitingTime[]=new int[n];
				waitingTime(n, executionTime, waitingTime,arrivalTime);
				for (int i=0;i<n;i++) {
					totalWaitingTime+=waitingTime[i];
				}
				double averageWaitingTime=(double)totalWaitingTime/(double)n;
				System.out.println(averageWaitingTime);
			}
			private static void waitingTimeForRR(int n,int executionTime[],int arrivalTime[],int quantum,int waitingTime[]) {
				int remainingExecutionTime[]=new int[n];
				for(int i=0; i<n;i++) {
					remainingExecutionTime[i]=executionTime[i];
				}
				int time=0;
				while(true) {
					boolean done=true;
					for(int i=0;i<n;i++) {
						if(remainingExecutionTime[i]>0) {
							done=false;
							if(remainingExecutionTime[i]>quantum) {
								time=time+quantum;
								remainingExecutionTime[i]-=quantum;
							}
							else {
								time=time+remainingExecutionTime[i];
								waitingTime[i]=time-executionTime[i]-arrivalTime[i];
								remainingExecutionTime[i]=0;
							}
						}
					}
					if(done==true) {
						break;
					}
				}
			}
			private static void sortForSJF(int n,int executionTime[],int arrivalTime[]) {
				int executionTimeForSJF[]=new int[n];
				int arrivalTimeForSJF[]=new int[n];
				for(int i=0;i<n;i++) {
					executionTimeForSJF[i]=executionTime[i];
					arrivalTimeForSJF[i]=arrivalTime[i];
				}
				for(int i=2;i<n;i++) {// ?????????
					if(executionTimeForSJF[i-1]>executionTimeForSJF[i]) {
						int temp=executionTimeForSJF[i-1];
						executionTimeForSJF[i-1]=executionTimeForSJF[i];
						executionTimeForSJF[i]=temp;
						temp=arrivalTimeForSJF[i-1];
						arrivalTimeForSJF[i-1]=arrivalTimeForSJF[i];
						arrivalTimeForSJF[i]=temp;
					}
				}
				System.out.print("Average waiting time when using SJF is:");
				averageWaitingTime(n,executionTimeForSJF,arrivalTime);
			}
			private static void averageWaitingTimeFCFS(int n, int executionTime[],int arrivalTime[]) {
				System.out.print("Average waiting time when using FCFS is:");
				averageWaitingTime(n,executionTime,arrivalTime);
			}
			private static void averageWaitingTimeForSJF(int n,int executionTime[],int arrivalTime[]){
				sortForSJF(n,executionTime,arrivalTime);
			}
			private static void averageWaitingTimeForRR(int n,int executionTime[],int arrivalTime[],int quantum) {
				int waitingTimeForRR[]=new int[n];
				waitingTimeForRR(n,executionTime,arrivalTime,quantum,waitingTimeForRR);
				int totalWaitingTime=0;
				for (int i=0;i<n;i++) {
					totalWaitingTime+=waitingTimeForRR[i];
				}
				double averageWaitingTime=(double)totalWaitingTime/(double)n;
				System.out.println("Average waiting time when using RR is:"+ averageWaitingTime);
			}
			 public static void main(String[]args) {
			        int n = 100; 
			        int quantum=4;
			        //Burst time of all processes
					Random r = new Random();
					int arrivalTime[]= new int[n];
					int[] executionTime = new int[n];
					for (int i = 0; i < executionTime.length; i++) {
					      executionTime[i] = r.nextInt(50)+1;
					      arrivalTime[i]=r.nextInt(50);
				}
					averageWaitingTimeForSJF(n,executionTime,arrivalTime);
					averageWaitingTimeFCFS(n,executionTime,arrivalTime);
					averageWaitingTimeForRR(n,executionTime,arrivalTime,quantum);
			 }
	}

