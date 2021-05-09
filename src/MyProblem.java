import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class MyProblem {
    public static int numTasks = 10;

    public static void main(String[] args) {
        System.out.println("Starting main...");
        int numProducer = Integer.parseInt(args[0]);
        int numConsumer = Integer.parseInt(args[1]);
        int capacity = Integer.parseInt(args[2]);
        CountDownLatch latch = new CountDownLatch(numConsumer + numProducer);
        LinkedBlockingQueue<Integer> buffer = new LinkedBlockingQueue<>(capacity);
        ArrayList<Thread> consumerList = new ArrayList<>(numConsumer);
        ArrayList<Thread> producerList = new ArrayList<>(numProducer);

        for (int i = 0; i < numProducer; i++) {
            Thread producer = new Thread(new Producer(i, buffer, latch));
            producerList.add(producer);
        }

        for (int i = 0; i < numConsumer; i++) {
            Thread consumer = new Thread(new Consumer(i, buffer, latch));
            consumerList.add(consumer);
        }

        for (Thread producer : producerList
        ) {
            producer.start();
        }
        for (Thread consumer : consumerList
        ) {
            consumer.start();
        }
        try {
            latch.await();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        System.out.println("Ending main...");
    }
}
