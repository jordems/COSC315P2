public class Consumer extends Thread {
    private BoundedBuffer boundedBuffer;

    public Consumer(BoundedBuffer buffer) {
        boundedBuffer = buffer;
    }

    // Consumes jobs and sleeps for job duration
    public void run() {
        try {
            while (true) {
                Job job = boundedBuffer.get();
                System.out.print("Consumed Job " + job.getID());
                sleep(job.getDuration());
            }
        } catch (InterruptedException e) {
        }
    }
}