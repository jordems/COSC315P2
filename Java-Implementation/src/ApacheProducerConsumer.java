
public class ApacheProducerConsumer {

    /**
     * @param numSlaves   Number of Consumers
     * @param maxDuration Max duration of a request
     */
    public void run(int numSlaves, int maxDuration) {
        System.out.printf("APC: numSlaves: %d - maxDuration: %d\n", numSlaves, maxDuration);
        //
        BoundedBuffer boundedBuffer = new BoundedBuffer(500);
        Producer producer = new Producer(boundedBuffer, maxDuration);
        // Run the Producer
        producer.start();

        // Initalize Consumers
        for (int i = 0; i < numSlaves; i++) {
            Consumer consumer = new Consumer(boundedBuffer);
            consumer.start();
        }



//        // Run each of the consumers
//        for (Consumer consumer : consumers) {
//            consumer.run();
//        }

    }
}
