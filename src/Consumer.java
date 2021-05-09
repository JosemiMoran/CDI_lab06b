import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable {
    private final LinkedBlockingQueue<Integer> buffer;
    private final CountDownLatch latch;
    private final int id;

    public Consumer(int id, LinkedBlockingQueue<Integer> buffer, CountDownLatch latch) {
        this.buffer = buffer;
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        int randomTime = ThreadLocalRandom.current().nextInt(0, 1000);
        try {
            Thread.sleep(randomTime);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        try {
            System.out.println("Consumer " + id + " consumed " + this.buffer.take());
            this.latch.countDown();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        System.out.println("Consumer " + id + " has finished");
    }
}
