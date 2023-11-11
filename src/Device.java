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
    private int connectDevice(){
        int connection = router.Connect();
        if (connection == -1) {
            System.out.println("(" + this.Name + ")(" + this.Type + ") arrived and waiting");
        } else {
            System.out.println("(" + this.Name + ") " + "(" + this.Type + ") " + "arrived");
        }
        try {
            semaphore.Wait();
        } catch (InterruptedException e) {
            System.out.println("Device is interrupted.");
        }
        if (connection == -1) connection = router.Connect();
        System.out.println("Connection " + (connection + 1) + ": " + this.Name + " Occupied");
        System.out.println("Connection " + (connection + 1) + ": " + this.Name + " login");
        return connection;
    }
    private void doActivity(int connection){
        System.out.println("Connection " + (connection + 1) + ": " + this.Name + " performs online activity");
        try {
            Thread.sleep(1000); // Simulating online activity
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private void disconnectDevice(int connection){
        System.out.println("Connection " + (connection + 1) + ": " + Name + " Logged out");
        router.Disconnect(connection);
        semaphore.signal();
    }
    @Override
    public void run() {
        int connection = connectDevice();
        doActivity(connection);
        disconnectDevice(connection);
    }

}