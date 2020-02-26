import java.util.LinkedList;
import java.util.Queue;

public class BoundedBuffer {
    private static BoundedBuffer boundedBuffer = new BoundedBuffer(5);
    private static Job[] requestQueue;

    private BoundedBuffer(int size) {
        requestQueue = new Job[size];
    }

    public static BoundedBuffer getInstance() {
        return boundedBuffer;
    }

    // TODO Dylan
    public static synchronized void put(Job job) throws InterruptedException {

    }

    // TODO Dylan
    public static synchronized Job get() throws InterruptedException {
        return null;
    }
}