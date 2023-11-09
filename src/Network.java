import java.util.*;

import static java.lang.Thread.sleep;

public class Network {
    public static void main(String[] args) throws InterruptedException {
        int NumberOfConnections;
        int NumberOfDevices;
        List<Device> devices = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("What is the number of WI-FI Connections?");
        NumberOfConnections = input.nextInt();
        Router router = new Router(NumberOfConnections);
        System.out.println("What is the number of devices Clients want to connect?");
        NumberOfDevices = input.nextInt();
        for (int i = 0; i < NumberOfDevices; i++) {
            Device D = new Device(input.next(), input.next(),router);
            devices.add(D);
        }
        for (int i = 0; i < NumberOfDevices; i++) {
            sleep(100);
            devices.get(i).start();
        }

    }
}
