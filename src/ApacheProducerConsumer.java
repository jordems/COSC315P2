
public class ApacheProducerConsumer {
    private BoundedBuffer boundedBuffer;

    public ApacheProducerConsumer() {
        boundedBuffer = BoundedBuffer.getInstance();
    }

    /**
     * TODO Jordan
     * 
     * @param numSlaves   Number of Consumers
     * @param maxDuration Max duration of a request
     */
    public void run(int numSlaves, int maxDuration) {
        Producer p1 = new Producer();

        Consumer c1 = new Consumer();

        System.out.printf("numSlaves: %d - maxDuration: %d\n", numSlaves, maxDuration);
    }
}