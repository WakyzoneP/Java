package eu.dice.hpc;

public class MyRThreadArray implements Runnable {

    private int[] numbers;
    private int startIndex;
    private int stopIndex;
    private long sum;
    
    public MyRThreadArray(int[] numbers, int startIndex, int stopIndex) {
        this.numbers = numbers;
        this.startIndex = startIndex;
        this.stopIndex = stopIndex;
    }
    @Override
    public void run() {
        this.sum = 0;
        for (var i = startIndex; i <= stopIndex; i++) {
            this.sum += numbers[i];
        }
    }

    public long getSum() {
        return sum;
    }
    public int[] getNumbers() {
        return numbers;
    }
    public int getStartIndex() {
        return startIndex;
    }
    public int getStopIndex() {
        return stopIndex;
    }

}
