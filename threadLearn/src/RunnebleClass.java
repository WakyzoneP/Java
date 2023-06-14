
public class RunnebleClass implements Runnable {
    private int low;
    private int high;
    private int[] elements;
    private long sum;

    public RunnebleClass(int low, int high, int[] elements){
        this.low = low;
        this.high = high;
        this.elements = elements;
    }

    @Override
    public void run() {
        sum = 0;
        for(int i = low; i < high; ++i)
            sum += elements[i];
    }

    public long getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int[] getElements() {
        return elements;
    }

    public void setElements(int[] elements) {
        this.elements = elements;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }
}
