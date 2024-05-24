//this is the class named 'Multithread.java'
public class Multithread {
    public static void main(String[] args) {
        int n = 8; // Number of threads
        long range = 1_000_000_000L; // Range to sum

        // Create an array to hold the sum results from each thread
        SumThread[] threads = new SumThread[n];

        long chunkSize = range / n; // Size of the range each thread will process

        for (int i = 0; i < n; i++) {
            long start = i * chunkSize + 1;
            long end = (i + 1) * chunkSize;
            threads[i] = new SumThread(start, end);
            threads[i].start();
        }

        long totalSum = 0;

        // Wait for all threads to complete and aggregate the results
        try {
            for (int i = 0; i < n; i++) {
                threads[i].join();
                totalSum += threads[i].getSum();
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        System.out.println("Total sum from 1 to 1e9 is: " + totalSum);
    }
}

//create another class
// this class name is 'SumThread.java'

class SumThread extends Thread {
    private long start;
    private long end;
    private long sum;

    public SumThread(long start, long end) {
        this.start = start;
        this.end = end;
        this.sum = 0;
    }

    public void run() {
        for (long i = start; i <= end; i++) {
            sum += i;
        }
    }

    public long getSum() {
        return sum;
    }
}

