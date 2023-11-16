class Semaphore {
    protected int value;

    protected Semaphore(int initial) {
        value = initial;
    }

    public synchronized void Wait(String name, String type) throws InterruptedException {
        value--;
        if (value < 0) {
            try {
                Network._print("(" + name + ")(" + type + ") arrived and waiting");
                wait();
                return;
            } catch (InterruptedException e) {
                Network._print("Device has been interrupted while waiting.");
            }
        }
        Network._print("(" + name + ") " + "(" + type + ") " + "arrived");
        return;
    }

    public synchronized void signal() {
        value++;
        if (value <= 0) {
            notify();
        }
    }
}