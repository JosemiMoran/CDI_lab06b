import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable {
    private final LinkedBlockingQueue<String> buffer;
    private final int id;
    private final CyclicBarrier cyclicBarrier;

    public Consumer(int id, LinkedBlockingQueue<String> buffer, CyclicBarrier cyclicBarrier) {
        this.buffer = buffer;
        this.id = id;
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
        round();
        round();
        round();
        round();
        System.out.println("Consumer " + id + " has finished");
    }

    private void round() {
        try {
            System.out.println("Consumer " + id + " consumed " + this.buffer.take());
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}
