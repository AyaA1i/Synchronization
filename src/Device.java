public class Device extends Thread {
    private String Name;
    private String Type;
    private Router router;
    private Semaphore semaphore;

    public Device(String name, String type, Router router, Semaphore semaphore) {
        this.Name = name;
        this.Type = type;
        this.router = router;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        int connection = router.Connect();
        if (connection == -1) {
            System.out.println("(" + this.Name + ")(" + this.Type + ") arrived and waiting");
            try {
                semaphore.Wait();
            } catch (InterruptedException e) {
                System.out.println("Device is interrupted.");
            }
            connection = router.Connect();
        } else {
            System.out.println("(" + this.Name + ") " + "(" + this.Type + ") " + "arrived");
        }

        System.out.println("Connection " + (connection + 1) + ": " + this.Name + " Occupied");
        System.out.println("Connection " + (connection + 1) + ": " + this.Name + " login");
        System.out.println("Connection " + (connection + 1) + ": " + this.Name + " performs online activity");
        // Perform online activity
        // Simulate online activity with random waiting time

        try {
            Thread.sleep(1000); // Simulating online activity
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Connection " + (connection + 1) + ": " + Name + " Logged out");
        router.Disconnect(connection);
        semaphore.signal();
    }
}