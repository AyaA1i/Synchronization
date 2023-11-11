import static java.lang.Thread.sleep;

class Semaphore {
    protected int value;

    protected Semaphore(int initial) {
        value = initial;
    }

    public synchronized void Wait() throws InterruptedException {
        value--;
        if (value < 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Device has been interrupted while waiting.");
            }
        }
    }

    public synchronized void signal() {
        value++;
        if (value <= 0) {
            notify();
        }
    }
}