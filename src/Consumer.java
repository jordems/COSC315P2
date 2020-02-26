public class Consumer extends Thread {
    private BoundedBuffer boundedBuffer;

    public Consumer() {
        boundedBuffer = BoundedBuffer.getInstance();
    }

    // TODO Levi
    public void run() {
        try {
            while (true) {
                Job job = boundedBuffer.get();
                System.out.print("Consumed Request " + job.getID());
                sleep(job.getDuration());

            }
        } catch (InterruptedException e) {
        }
    }
}