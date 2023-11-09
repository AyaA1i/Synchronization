
class Semaphore{
    protected int value;
    protected Semaphore(int initial) {
        value = initial ;
    }
    public synchronized void wait(Device device) throws InterruptedException {
        value-- ;
        if (value < 0){
            try {
                System.out.println(device.Name + " (" + device.Type + ")" + " arrived and waiting");
                wait() ;
            }
            catch( InterruptedException e ) {
                System.out.println("Device has been interrupted while waiting.");
            }
        }else{
            System.out.println( device.Name +" (" + device.Type + ")" +" arrived");
        }
        device.router.connect(device);
    }
    public synchronized void signal() {
        value++ ;
        if (value <= 0) {
            notify() ;
        }
    }
}