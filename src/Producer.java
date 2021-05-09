import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {

    private final LinkedBlockingQueue<Integer> buffer;
    private final CountDownLatch latch;
    private final int id;
    private int value;

    public Producer(int id, LinkedBlockingQueue<Integer> buffer, CountDownLatch latch) {
        this.id = id;
        this.buffer = buffer;
        this.latch = latch;
        value = 0;
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
            this.buffer.put(value++);
            this.latch.countDown();
            System.out.println("Producer " + id + " produced " + value);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        System.out.println("Producer " + id + " has finished");
    }
}
