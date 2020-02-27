import java.util.Random;

public class Producer extends Thread {

    private BoundedBuffer boundedBuffer;
    private int count = 0;

    public Producer(BoundedBuffer buffer) {
        boundedBuffer = buffer;
    }

    // Generates and puts Jobs in the request queue, sleeps for random short
    // duration
    public void run(int maxDuration) {
        try {

            Random randomGenerator = new Random();

            while (true) {

                int randomInt = randomGenerator.nextInt(maxDuration);
                int randomDuration = randomGenerator.nextInt(5);

                System.out.print("Producer sleeping for " + randomDuration + " seconds.");

                sleep(randomDuration);

                Job job = new Job();
                job.setID(count);
                job.setDuration(randomInt);

                System.out.print("Produced Job " + job.getID());

                boundedBuffer.put(job);

                count++;

            }

        } catch (InterruptedException e) {
        }
    }
}