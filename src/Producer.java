import java.util.Random;

public class Producer extends Thread {

    private BoundedBuffer boundedBuffer;
    private int count = 0;

    public Producer() {
        boundedBuffer = BoundedBuffer.getInstance();
    }

    // Generates and puts Jobs in request queue
    public void run(int randomDuration, int maxDuration) {
        try {

            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(maxDuration) + 1;

            Job job = new Job();
            job.setID(count);
            count++;
            job.setDuration(randomInt);
            System.out.print("Produced Request " + job.getID());
            System.out.print("Sleeping for " + randomDuration + " seconds.");
            boundedBuffer.put(job);
            sleep(randomDuration);

        } catch (InterruptedException e) {
        }
    }
}