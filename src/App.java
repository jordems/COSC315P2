import java.util.Scanner;

public class App {
    private static ApacheProducerConsumer apc;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        System.out.print("# Consumers: ");
        int numSlaves = in.nextInt();
        System.out.print("Max Duration: ");
        int maxDuration = in.nextInt();
        System.out.println();

        in.close();

        apc = new ApacheProducerConsumer();
        apc.run(numSlaves, maxDuration);

    }
}
