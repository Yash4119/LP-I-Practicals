import java.util.*;

public class round_robin {

	static void swap(int arr [], int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void main (String args[]) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of Processes");
		int n = sc.nextInt();
		System.out.println("Enter the Time Quantum");
		int quant = sc.nextInt();
		
		int at [] = new int[n];
		int bt [] = new int[n];
		int temp_burst [] = new int[n];
		int pid [] = new int[n];
		int ct[] = new int[n];
		int tat [] = new int[n];
		int wt [] = new int[n];
		float avgtat=0,avgwt=0;
		int st=0;
		 Queue<Integer> q = new LinkedList<>();
		
		System.out.println("Enter the Arrival time for all processes");
		for(int i=0;i<n;i++) {
			pid[i] = i+1;
			at[i] = sc.nextInt();
		}
		
		System.out.println("Enter the Burst time for all processes");
		for(int i=0;i<n;i++) {
			bt[i] = sc.nextInt();
			temp_burst[i] = bt[i];
		}
		
		for(int i = 0 ; i <n; i++)
		{
			for(int  j=0;  j < n-(i+1) ; j++)
			{
				if( at[j] > at[j+1] )
				{
					swap(at,j,j+1);
					swap(bt,j,j+1);
					swap(pid,j,j+1);
				}
			}
		}
		
		
			
		while(true) {
			
			boolean flag = true;
			
			for(int i=0;i<n;i++) {
				if(at[i] >= st && bt[i] > 0) {
					q.add(i);
				}
			}
			
			if(at[0] > st) {
				st = at[0];
			}
			 
	        while (!q.isEmpty()) {
	        	
	        	int ind = q.remove();
	        	
	        	if(st>=at[ind] && bt[ind]>0) {
					if(bt[ind] <= quant){
						st += bt[ind];
						bt[ind] = 0;

						ct[ind] = st;
						continue;
					}
					else {
						bt[ind] -= quant;
						st += quant;
					}
					
					flag=false;
				}
	        	
	        	q.add(ind);
	        	
	        }
			
			if(flag) {
				break;
			}
		}
			
		for(int i=0;i<n;i++) {
			tat[i] = ct[i] - at[i] ;     
			wt[i] = tat[i] - temp_burst[i] ;  
			avgwt += wt[i] ;   
			avgtat += tat[i] ;
		}
		
		System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
		for(int  i = 0 ; i< n;  i++)
		{
		System.out.println(pid[i] + "  \t " + at[i] + "\t" + temp_burst[i] + "\t" + ct[i] + "\t" + tat[i] + "\t"  + wt[i] ) ;
		}
		sc.close();
		System.out.println("\nAverage TAT = "+ (avgwt/n));  
		System.out.println("Average WT = "+(avgtat/n));  
		}
	}

