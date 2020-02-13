import java.util.LinkedList;
import java.util.Queue;

public class BoundedBuffer {
    private static BoundedBuffer boundedBuffer = new BoundedBuffer();
    private static Queue<Job> queue = new LinkedList<Job>();

    private BoundedBuffer() {
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