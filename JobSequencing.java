import java.util.*;

class Job {
    String id;
    int deadline;
    int profit;

    public Job(String id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobSequencing {
    public static void jobSequencing(Job[] jobs) {
        // Step 1: Sort jobs by profit in descending order
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Find maximum deadline to size the time slots
        int maxDeadline = 0;
        for (Job job : jobs) {
            if (job.deadline > maxDeadline) {
                maxDeadline = job.deadline;
            }
        }

        // Step 2: Initialize time slots, -1 means free slot
        String[] slots = new String[maxDeadline];
        Arrays.fill(slots, null);

        int totalProfit = 0;

        // Step 3: Assign jobs to slots
        for (Job job : jobs) {
            // Try to schedule job in the latest possible slot before deadline
            for (int j = job.deadline - 1; j >= 0; j--) {
                if (slots[j] == null) {
                    slots[j] = job.id;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        // Step 4: Print scheduled jobs
        System.out.println("Scheduled Jobs:");
        for (String jobId : slots) {
            if (jobId != null) {
                System.out.println("Job ID: " + jobId);
            }
        }

        System.out.println("Total Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        // Example jobs
        Job[] jobs = {
            new Job("a", 2, 100),
            new Job("b", 1, 19),
            new Job("c", 2, 27),
            new Job("d", 1, 25),
            new Job("e", 3, 15)
        };

        jobSequencing(jobs);
    }
}