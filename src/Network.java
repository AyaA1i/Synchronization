import java.util.*;
public class Network {
    public static void main(String[] args){
        int NumberOfConnections;
        int NumberOfDevices;
        List<Device> devices = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("What is the number of WI-FI Connections?");
        NumberOfConnections = input.nextInt();
        Router router = new Router(NumberOfConnections);
        System.out.println("What is the number of devices Clients want to connect?");
        NumberOfDevices = input.nextInt();
        Semaphore semaphore = new Semaphore(NumberOfConnections);
        for (int i = 0; i < NumberOfDevices; i++) {
            Device D = new Device(input.next(), input.next(), router, semaphore);
            devices.add(D);
        }
        for (int i = 0; i < NumberOfDevices; i++) {
            devices.get(i).start();
        }

    }
}
