import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {

    private final LinkedBlockingQueue<String> buffer;
    private final int id;
    private final CyclicBarrier cyclicBarrier;

    public Producer(int id, LinkedBlockingQueue<String> buffer, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.buffer = buffer;
        this.cyclicBarrier = cyclicBarrier;
    }


    @Override
    public void run() {
        int randomTime = ThreadLocalRandom.current().nextInt(0, 1000);
        try {
            Thread.sleep(randomTime);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        round("Starter");
        round("Main Course");
        round("Dessert");
        round("Coffee");
        System.out.println("Producer " + id + " has finished");
    }

    private void round(String dish) {
        try {
            this.buffer.put(dish);
            System.out.println("Producer " + id + " produced " + dish);
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}

