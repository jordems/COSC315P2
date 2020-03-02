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
                long start = System.currentTimeMillis();
                System.out.println("Consumer: " + this.getId() + ", Consuming Job " + job.getID() + " of duration: " + job.getDuration() + " seconds");
                sleep(job.getDuration() * 1000);
                long time = System.currentTimeMillis() - start;
                System.out.println("Consumer: " + this.getId() + ", Job " + job.getID() + " completed in time: " + (time/1000) + " seconds.");
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
