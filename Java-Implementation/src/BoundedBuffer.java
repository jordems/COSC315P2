import java.util.ArrayList;
import java.util.List;

public class BoundedBuffer {
    private Job[] buffer;
    private int bufferSize;
    private int count = 0;
    private int putIndex = 0;
    private int getIndex = 0;

    BoundedBuffer(int bufferSize) throws IllegalArgumentException {
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("Buffer must be greater than 1");
        }
        this.bufferSize = bufferSize;
        this.buffer = new Job[bufferSize];
    }

    public synchronized void put(Job job) throws InterruptedException {
        while (count == bufferSize) {
            wait();
        }

        buffer[putIndex] = job;
        putIndex++;
        if(putIndex > bufferSize - 1) {
            putIndex = 0;
        }
        count++;
        notifyAll();
    }

    public synchronized Job get() throws InterruptedException {
        while (count == 0) {
            wait();
        }

        Job job = buffer[getIndex];
        getIndex++;
        if(getIndex > bufferSize - 1) {
            getIndex = 0;
        }
        count--;
        notifyAll();
        return job;
    }
}
