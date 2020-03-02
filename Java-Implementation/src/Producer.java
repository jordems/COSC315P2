import java.util.Random;

public class Producer extends Thread {

    private BoundedBuffer boundedBuffer;
    private int maxDuration;

    public Producer(BoundedBuffer buffer, int maxDuration) {
        boundedBuffer = buffer;
        this.maxDuration = maxDuration;
    }

    // Generates and puts Jobs in the request queue, sleeps for random short
    // duration
    public void run() {
        try {

            Random randomGenerator = new Random();
            int count = 0;

            while (true) {

                int randomInt = randomGenerator.nextInt(this.maxDuration) + 1;
                int randomDuration = randomGenerator.nextInt(5) + 1;

                System.out.println("Producer sleeping for " + randomDuration + " seconds.");

                sleep(randomDuration * 1000);

                Job job = new Job();
                job.setID(count);
                job.setDuration(randomInt);

                System.out.println("Produced Job " + job.getID());

                boundedBuffer.put(job);

                count++;

            }

        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
