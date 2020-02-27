import java.util.ArrayList;
import java.util.List;

public class BoundedBuffer {
    private List<Job> buffer;
    private int bufferSize;
    private int currentIndex = 0;

     BoundedBuffer(int bufferSize) throws IllegalArgumentException {
        if(this.bufferSize <= 0) {
            throw new IllegalArgumentException("Buffer must be greater than 1");
        }
        this.bufferSize = bufferSize;
        this.buffer = new ArrayList<Job>(bufferSize);
    }

    public synchronized void put(Job job) throws InterruptedException {
        while (buffer.size() == bufferSize) {
            wait();
        }

        buffer.add(job);
        currentIndex++;
        notifyAll();
    }

    public synchronized Job get() throws InterruptedException {
        while (buffer.size() == 0) {
            wait();
        }

        Job job = buffer.remove(currentIndex);
        currentIndex--;
        notifyAll();
        return job;
    }
}
