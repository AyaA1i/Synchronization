class Device extends Thread {
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

    private int connectDevice() {
        try {
            semaphore.Wait(this.Name, this.Type);
        } catch (InterruptedException e) {
            Network._print("Device is interrupted.");
            e.printStackTrace();
        }
        int connection = router.Connect();
        if (connection == -1) {
            Network._print("connection = -1 : " + this.Name);
            try {
                sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Network._print("Connection " + (connection + 1) + ": " + this.Name + " Occupied");
        Network._print("Connection " + (connection + 1) + ": " + this.Name + " login");
        return connection;
    }

    private void doActivity(int connection) {
        Network._print("Connection " + (connection + 1) + ": " + this.Name + " performs online activity");
        try {
            Thread.sleep(1000); // Simulating online activity
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void disconnectDevice(int connection) {
        Network._print("Connection " + (connection + 1) + ": " + Name + " Logged out");
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