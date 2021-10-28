import threading.CircularBuffer;
import data.RootData;
import threading.RootFinder;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main root solver class which runs the terminal interface for testing
 * the multithreaded root finder and handles creating/destroying the threads.
 * @see RootFinder
 */
public class Master {

    /**
     * Main method for running the command line interface for selecting which test you would like to run.
     * Options 1 or 2 are displayed to the user, and response is prompted.
     * @param args  input args for method
     */
    public static void main(String[] args) {
        boolean valid = false;
        int selection = -1;

        while (!valid) {
            System.out.println("Select option 1 or 2:");
            System.out.println("1. Perform small test (30 3-tuples)");
            System.out.println("2. Perform large test (3000 3-tuples)");
            System.out.print("select: ");
            Scanner scanner = new Scanner(System.in);
            selection = scanner.nextInt();
            if (selection > 0 && selection < 3) {
                valid = true;
            } else {
                System.out.println("Invalid selection, number must be 1 or 2.");
            }
        }

        switch (selection) {
            case 1 -> smallTest();
            case 2 -> largeTest();
        }
        System.exit(1);
    }

    /**
     * Performs a large root solving test with 3000 3-tuples to be computed, distributed over 10 threads.
     * Once all roots are computed the number of roots sets each thread computed is displayed.
     * @see RootData
     * @see CircularBuffer
     * @see ExecutorService
     */
    public static void largeTest() {
        System.out.println("running large test....");

        int threadCount = 10;
        int rootsToTest = 3000;

        CircularBuffer<double[]> inputBuffer = new CircularBuffer<double[]>(threadCount);
        CircularBuffer<RootData> outputBuffer = new CircularBuffer<RootData>(rootsToTest);

        //Create threads:
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i<threadCount; i++) {
            executorService.execute(new RootFinder(inputBuffer, outputBuffer));
        }

        executorService.shutdown();

        //Add random a,b,c to root finder buffer
        for (int i = 0; i<rootsToTest; i++) {
            Random rand = new Random();
            double[] newABC = {rand.nextDouble()*20.0+0.1, rand.nextDouble()*20.0+0.1, rand.nextDouble()*20.0+0.1};
            try {
                inputBuffer.blockingPut(newABC);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Read output roots
        HashMap<Thread, Integer> rootsPerThread = new HashMap<>();

        System.out.println("OUTPUTS:");
        while (!outputBuffer.isEmpty()) {
            try {
                Thread computingThread = outputBuffer.blockingGet().getThread();
                if (rootsPerThread.containsKey(computingThread)) {
                    rootsPerThread.put(computingThread, rootsPerThread.get(computingThread)+1);
                } else {
                    rootsPerThread.put(computingThread, 1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Thread thread : rootsPerThread.keySet()) {
            System.out.println("Thread: " + thread.toString() + " computed " + rootsPerThread.get(thread).toString() + " roots");
        }

        executorService.shutdown();
    }

    /**
     * Performs a small test for the root finder by running 30 3-tuples through the RootFinder class
     * with workload distributed over 10 threads. Computed roots are simultaneously outputed after computed
     * with the thread that computed them also displayed.
     * @see RootFinder
     * @see CircularBuffer
     * @see ExecutorService
     */
    public static void smallTest() {
        System.out.println("running small test....");

        int threadCount = 10;
        int rootsToTest = 30;
        CircularBuffer<double[]> inputBuffer = new CircularBuffer<double[]>(threadCount);
        CircularBuffer<RootData> outputBuffer = new CircularBuffer<RootData>(rootsToTest);

        //Create threads:
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i<threadCount; i++) {
            executorService.execute(new RootFinder(inputBuffer, outputBuffer));
        }

        //Add random a,b,c to root finder buffer
        for (int i = 0; i<rootsToTest; i++) {
            Random rand = new Random();
            double[] newABC = {rand.nextDouble()*20.0+0.1, rand.nextDouble()*20.0+0.1, rand.nextDouble()*20.0+0.1};
            try {
                inputBuffer.blockingPut(newABC);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Read output roots
        System.out.println("OUTPUTS:");
        while (!outputBuffer.isEmpty()) {
            try {
                System.out.println(outputBuffer.blockingGet().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-----------------------------");
        }
        executorService.shutdownNow();
    }

}
