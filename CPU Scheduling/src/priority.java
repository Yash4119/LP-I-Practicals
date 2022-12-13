import java.util.*;
 
public class priority 
{
        
    public static void main(String args[])
    {
    	Scanner sc=new Scanner(System.in);
		System.out.println("Enter no of process : ");
		int n= sc.nextInt();
		int pid[] = new int[n];
		int at[] = new int[n];
		int bt[] = new int[n];
		int prio[] = new int[n];
		int ct[] = new int[n];
		int tat[] = new int[n];
		int wt[] = new int[n];
		int f[] = new int [n];
	    int i,temp, st=0, tot=0;
	    float avg_wt=0, avg_tat=0;
	    
	    for (i=0;i<n;i++)
	    {
	    	pid[i]= i+1;
	    	System.out.println ("Enter arrival time of process " +(i+1)+ ":");
	    	at[i]= sc.nextInt();
	    	System.out.println("Enter burst time of process " +(i+1)+ ":");
	    	bt[i]= sc.nextInt();
	    	System.out.println("Enter priority of process " +(i+1)+ ":");
	    	prio[i]= sc.nextInt();
	    }
	    
	    for(i=0 ; i<n; i++)
		{
			for(int j=0;  j<n-(i+1) ; j++)
			{
				if( at[j] >= at[j+1])
				{
					temp = at[j];
					at[j] = at[j+1];
					at[j+1] = temp;
					temp = bt[j];
					bt[j] = bt[j+1];
					bt[j+1] = temp;
					temp = pid[j];
					pid[j] = pid[j+1];
					pid[j+1] = temp;
					temp=prio[j];
				    prio[j]=prio[j+1];
				    prio[j+1]=temp;
				}
			}
		}
	    
	    
	    while(true)
	    {
	    	int min=99,c=n;
	    	if (tot==n)
	    		break;
	    	
	    	for (i=0;i<n;i++)
	    	{
	    		if ((at[i]<=st) && (f[i]==0) && (bt[i]<min))
	    		{	
	    			min=bt[i];
	    			c=i;
	    		}
	    	}
	    	
	    	if (c==n)
	    		st++;
	    	else
	    	{
	    		ct[c]= st+bt[c];
	    		st=st+bt[c];
	    		f[c]=1;
	    		tot++;
	    	}
	    	
	    	for(i=0 ; i<n; i++)
			{
				for(int j=0;  j<n-(i+1) ; j++)
				{
					if( st>at[j] && st>at[j+1] && f[j]==0)
					{
						if(prio[j]>prio[j+1] )
						{
						temp = at[j];
						at[j] = at[j+1];
						at[j+1] = temp;
						temp = bt[j];
						bt[j] = bt[j+1];
						bt[j+1] = temp;
						temp = pid[j];
						pid[j] = pid[j+1];
						pid[j+1] = temp;
						temp=prio[j];
					    prio[j]=prio[j+1];
					    prio[j+1]=temp;
						}
					}
				}
				
			}
	    }
	    
	    for(i=0 ; i<n; i++)
		{
			if( i == 0)
			{	
				ct[i]= at[i]+bt[i];
			}
			else
			{
				if( at[i]>ct[i-1])
				{
					ct[i]= at[i] +bt[i];
					f[i]=1;
				}
				else
				{
					ct[i]= ct[i-1] +bt[i];
					f[i]=1;
				}
			}
			tat[i] = ct[i] - at[i] ;
			wt[i] = tat[i] - bt[i] ;
			avg_wt += wt[i] ;
			avg_tat += tat[i] ;
		}
 
		System.out.println("Pid\tAT\tBT\tPRIO\tCT\tTAT\tWT\n");
		for(i=0;i<n;i++)
		{
			System.out.println(pid[i] +"\t"+ at[i]+"\t"+bt[i] +"\t"+prio[i] +"\t"+ ct[i] +"\t"+ tat[i] +"\t"+ wt[i]);
		}
		System.out.print("\n Average Wait Time : "+avg_wt/n);
		System.out.print("\n Average Turn Around Time : "+avg_tat/n);
    }
}