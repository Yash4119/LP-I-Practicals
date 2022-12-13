import java.util.Scanner;
public class SJF_Preemtive {
	
	static void swap(int [] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n;
		System.out.println("Enter the number of processes :- ");
		n = sc.nextInt();
		
		int pid [] = new int[n];
		int at[] = new int[n];
		int bt[] = new int[n];
		int ct[] = new int[n];
		int tat[] = new int[n];
		int wt[] = new int[n];
		int rem_bt[] = new int[n];
		int flag[] = new int[n];
		float tat_avg=0,wt_avg=0;
		int tot=0,time=0;
		
		for(int i=0;i<n;i++) {
			System.out.println("Enter Arrival Time :- ");
			at[i] = sc.nextInt();
			System.out.println("Enter Burst Time :- ");
			bt[i] = sc.nextInt();
			pid[i] = i+1;
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<(n-i-1);j++) {
				if(at[j]>at[j+1]) {
					swap(at,j,j+1);
					swap(bt,j,j+1);
					swap(pid,j,j+1);
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			rem_bt[i] = bt[i];
		}
		
		while(true) {
			
			int min=99,c=n;
			if(tot==n) {
				break;
			}
			
			for(int i=0;i<n;i++) {
				if(at[i]<=time && flag[i]==0 && rem_bt[i] < min) {
					c = i;
					min = rem_bt[i];
				}
			}
			
			if(c==n) {
				time++;
			}
			else if(rem_bt[c] > 0){
				time += 1;
				rem_bt[c] -= 1;
			}
			else {
				flag[c] = 1;
				ct[c] = time;
				tot++;
			}
		}
		
		for(int i=0;i<n;i++) {
			tat[i] = ct[i]-at[i];
			wt[i] = tat[i]-bt[i];
			tat_avg += tat[i];
			wt_avg += wt[i];
		}
		System.out.println();
		
		System.out.println("PID\tAT\t BT\tCT\tTAT\tWT");
		for(int i=0;i<n;i++) { 
			System.out.println(pid[i] + " \t" + at[i] + " \t" + bt[i] + " \t " + ct[i] +  " \t " + tat[i] + " \t" + wt[i]);
		}
		System.out.println();
		sc.close();
		System.out.println("Average Turn Around Time is :- " + (tat_avg/n));
		System.out.println("Average Waiting Time is :- " + (wt_avg/n));
		sc.close(); 
	}

}
