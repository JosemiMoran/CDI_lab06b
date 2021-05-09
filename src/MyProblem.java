import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;

public class MyProblem {
    public static final int numTasks = 10;

    public static void main(String[] args) {
        System.out.println("Starting main...");
        int numProducer = Integer.parseInt(args[0]);
        int numConsumer = Integer.parseInt(args[1]);
        int capacity = Integer.parseInt(args[2]);
        LinkedBlockingQueue<String> buffer = new LinkedBlockingQueue<>(capacity);
        ArrayList<Thread> consumerList = new ArrayList<>(numConsumer);
        ArrayList<Thread> producerList = new ArrayList<>(numProducer);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(numProducer + numConsumer);

        for (int i = 0; i < numProducer; i++) {
            Thread producer = new Thread(new Producer(i, buffer, cyclicBarrier));
            producerList.add(producer);
        }

        for (int i = 0; i < numConsumer; i++) {
            Thread consumer = new Thread(new Consumer(i, buffer, cyclicBarrier));
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

     //   System.out.println("Ending main...");
    }
}
