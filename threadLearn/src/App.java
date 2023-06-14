import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws Exception {

        final int lenght = 40_000_000;
        final int threadsLenght = 8;
        long sum = 0;

        int[] numbers = new int[lenght];

        for(int i = 0; i < lenght; ++i) numbers[i] = i + 1;

        //1.
        Thread[] threads = new Thread[threadsLenght];
        RunnebleClass[] runnebleClasses = new RunnebleClass[threadsLenght];

        for(int i = 0; i < threadsLenght; ++i){
            int low = i * lenght / threadsLenght;
            int high = (i + 1) * lenght / threadsLenght - 1;

            runnebleClasses[i] = new RunnebleClass(low, high, numbers);
            threads[i] = new Thread(runnebleClasses[i]);
        }

        for(int i = 0; i < threadsLenght; ++i)
            threads[i].start();

        for(int i = 0; i < threadsLenght; ++i){
            try {
                threads[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < threadsLenght; ++i)
            sum += runnebleClasses[i].getSum();

        System.out.println("Sum is " + sum);

        //2.
        sum = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(threadsLenght);
        runnebleClasses = new RunnebleClass[threadsLenght];

        for(int i = 0; i < threadsLenght; ++i){
            int low = i * lenght / threadsLenght;
            int high = (i + 1) * lenght / threadsLenght - 1;

            runnebleClasses[i] = new RunnebleClass(low, high, numbers);
            executorService.execute(runnebleClasses[i]);
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(5, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0 ; i < threadsLenght; ++i)
            sum += runnebleClasses[i].getSum();

        System.out.println("Sum is " + sum);
    }
}
