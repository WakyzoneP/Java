package eu.dice.hpc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProgMainMultithreadinForParallel {
    private static final int NUM_THREADS = 4;
    // public static long sum;

    public static void main(String[] args) {
        int dimension = 40_000_000;
        long suma = Long.valueOf(0);

        int[] a = new int[dimension];

        long sum = 0;

        for (int i = 0; i < dimension; i++) {
            a[i] = 1 + i;
        }

        // 1. sequential
        long startTime = System.currentTimeMillis();

        for (var i = 0; i < dimension; i++) {
            sum += a[i];
        }

        suma = Long.valueOf(sum);

        long stopTime = System.currentTimeMillis();

        System.out.println("Sequential sum: " + suma + " in " + (stopTime - startTime) + " ms");

        // 2. multi-threading
        startTime = System.currentTimeMillis();
        sum = 0;

        Thread[] threads = new Thread[NUM_THREADS];

        MyRThreadArray[] vectorR = new MyRThreadArray[NUM_THREADS];

        int startIndex;
        int stopIndex;
        for (var iterator = 0; iterator < NUM_THREADS; iterator++) {
            startIndex = iterator * (dimension / NUM_THREADS);
            stopIndex = (iterator + 1) * (dimension / NUM_THREADS) - 1;

            vectorR[iterator] = new MyRThreadArray(a, startIndex, stopIndex);
            threads[iterator] = new Thread(vectorR[iterator]);
        }

        for (var iterator = 0; iterator < NUM_THREADS; iterator++) {
            threads[iterator].start();
        }

        for (var iterator = 0; iterator < NUM_THREADS; iterator++) {
            try {
                threads[iterator].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (var iterator = 0; iterator < NUM_THREADS; iterator++) {
            sum += vectorR[iterator].getSum();
        }

        stopTime = System.currentTimeMillis();

        suma = Long.valueOf(sum);

        System.out.println("Multi-tread sum: " + suma + " in " + (stopTime - startTime) + " ms");

        // 3. ExecutorService multi-threading

        startTime = System.currentTimeMillis();
        sum = 0;

        ExecutorService objThreadPoolExecutorService = Executors.newFixedThreadPool(NUM_THREADS);
        MyRThreadArray[] workerTask = new MyRThreadArray[NUM_THREADS];

        for (var iterator = 0; iterator < NUM_THREADS; iterator++) {
            startIndex = iterator * (dimension / NUM_THREADS);
            stopIndex = (iterator + 1) * (dimension / NUM_THREADS) - 1;

            workerTask[iterator] = new MyRThreadArray(a, startIndex, stopIndex);
            objThreadPoolExecutorService.execute(workerTask[iterator]);
        }

        objThreadPoolExecutorService.shutdown();

        try {
            objThreadPoolExecutorService.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (var iterator = 0; iterator < NUM_THREADS; iterator++) {
            sum += workerTask[iterator].getSum();
        }

        stopTime = System.currentTimeMillis();
        suma = Long.valueOf(sum);
        System.out.println("ExecutorService multi-tread sum: " + suma + " in " + (stopTime - startTime) + " ms");
    }
}
