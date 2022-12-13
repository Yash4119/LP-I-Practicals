import java.util.*;

class Process {
    int id;
    boolean active;

    Process(int id) {
        this.id = id;
        active = true;
    }
}

public class election_algo {
    static int no;
    static Process p[];

    public static int getMax() {
        int max = -99;
        int max_index = 0;
        for (int i = 0; i < p.length; i++) {
            if (p[i].active && p[i].id > max) {
                max = p[i].id;
                max_index = i;
            }
        }
        return max_index;
    }

    public static void main(String args[]) {
        int initiator;
        int c;
        int prev,next;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your Choice");
            System.out.println("1) Ring");
            System.out.println("2) Bully");
            System.out.println("3) Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1://ring
                    System.out.println("Enter the number of processes");
                    no = sc.nextInt();
                    p = new Process[no];
                    for (int i = 0; i < p.length; i++) {
                        p[i] = new Process(i);
                    }
        
                    System.out.println("Process with Process ID " + p[getMax()].id + " Fails");
                    p[getMax()].active = false;
        
                    System.out.print("Enter the process id who will initiate the election :");
                    initiator = sc.nextInt();
                    prev = initiator;
                    next = prev + 1;

                    while (true) {
                        if (p[next].active) {
                            System.out.println(
                                    "P" + p[prev].id + " Passes Msg 'Election(" + p[prev].id + ")' to P" + p[next].id);
                            prev = next;
                        }
                        next = (next + 1) % no;
                        if (next == initiator) {
                            break;
                        }
                    }
                    System.out.println("Process with Process ID " + p[getMax()].id + " becomes the coordinator");
                    // c stands for coordinator
                    c = p[getMax()].id;
                    prev = c;
                    next = (prev + 1) % no;
                    while (true) {
                        if (p[next].active) {
                            System.out.println(
                                    "P" + p[prev].id + " Passes Msg 'Coordinator(" + c + ")' to P" + p[next].id);
                            prev = next;
                        }
                        next = (next + 1) % no;
                        if (next == c) {
                            break;
                        }
                    }
                    break;
                case 2://bully
                    System.out.println("Enter the number of processes");
                    no = sc.nextInt();
                    p = new Process[no];
                    for (int i = 0; i < p.length; i++) {
                        p[i] = new Process(i);
                    }
        
                    System.out.println("Process with Process ID " + p[getMax()].id + " Fails");
                    p[getMax()].active = false;
        
                    System.out.print("Enter the process id who will initiate the election :");
                    initiator = sc.nextInt();
                    while (true) {
                        boolean is_high = false;
                        for (int i = initiator + 1; i < no; i++) {
                            if (p[i].active) {
                                System.out.println("P" + initiator + " Passes Msg 'Election(" + initiator + ")' to P" + i);
                                is_high = true;
                            }
                        }

                        if (is_high) {
                            for (int i = initiator + 1; i < no; i++) {
                                if (p[i].active) {
                                    System.out
                                            .println("Process" + i + " Passes Msg 'OKay(" + i + ")' to P" + initiator);
                                    is_high = true;
                                }
                            }
                            initiator++;
                        } 
                        else {
                            c = p[getMax()].id;
                            System.out.println("Process with Process ID " + p[getMax()].id + " becomes the coordinator");
                            for (int i = c - 1; i >= 0; i--) {
                                if (p[i].active) {
                                    System.out.println("P" + c + " Passes Msg 'Coordinator(" + c + ")' to P" + i);
                                }
                            }
                            break;
                        }
                    }
                    break;
                case 3:
                    System.exit(1);
                    break;
            }
        }
    }
}
