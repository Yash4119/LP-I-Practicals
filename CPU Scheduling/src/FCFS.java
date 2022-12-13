import java.util.Scanner;

public class FCFS {

	public static void swap(int [] a, int s1, int s2) {
		int temp = a[s1];
		a[s1] = a[s2];
		a[s2] = temp;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("enter no of process: ");
		int n = sc.nextInt();
		int pid[] = new int[n];
		int at[] = new int[n];   
		int bt[] = new int[n];   
		int ct[] = new int[n]; 
		int ta[] = new int[n];    
		int wt[] = new int[n];  
		int temp;
		float avgwt=0,avgta=0;
		 
		for(int i = 0; i < n; i++)
		{
		System.out.println("Enter PID " + (i+1) + " Arival Time: ");
		at[i] = sc.nextInt();
		System.out.println("Enter PID " + (i+1) + " Burst Time: ");
		bt[i] = sc.nextInt();
		pid[i] = i+1;
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
		
		
		for(int  i = 0 ; i < n; i++)
		{
			if( i == 0)
			{
				ct[i] = at[i] + bt[i];
			}
			else
			{
			if( at[i] > ct[i-1])
			{
				ct[i] = at[i] + bt[i];
			}
			else
				ct[i] = ct[i-1] + bt[i];
			}
			ta[i] = ct[i] - at[i] ;     
			wt[i] = ta[i] - bt[i] ;  
			avgwt += wt[i] ;   
			avgta += ta[i] ;               
		}
		System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
		for(int  i = 0 ; i< n;  i++)
		{
		System.out.println(pid[i] + "  \t " + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + ta[i] + "\t"  + wt[i] ) ;
		}
		sc.close();
		System.out.println("\nAverage TAT = "+ (avgwt/n));  
		System.out.println("Average WT = "+(avgta/n));  
		}
	}


