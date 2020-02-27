
public class ApacheProducerConsumer {

    /**
     *
     * 
     * @param numSlaves   Number of Consumers
     * @param maxDuration Max duration of a request
     */
    public void run(int numSlaves, int maxDuration) {
        System.out.printf("APC: numSlaves: %d - maxDuration: %d\n", numSlaves, maxDuration);
        //
        BoundedBuffer boundedBuffer = new BoundedBuffer(5);
        Producer producer = new Producer();
        Consumer[] consumers = new Consumer[5];

        // Initalize Consumers
        for (Consumer consumer : consumers) {
            consumer = new Consumer();
        }

        try {
            // Run the Producer
            producer.run(maxDuration);

            // Run each of the consumers
            for (Consumer consumer : consumers) {
                consumer.run();
            }
        } catch (InterruptedException e) {
            System.out.println("Produced Interruption Exception");
        }

    }
}