import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

class Network {
    static Path currentDirectory = Path.of(System.getProperty("user.dir"));
    public static BufferedWriter buf = null;
    public static File redirectFile = null;
    public static int write_mode = 1;

    /**
     * Initializes the buffer to write into
     *
     * @return true if success, false otherwise.
     * @author Adham Allam
     */
    public static boolean initBuffer() {
        if (write_mode == 0)
            return false;

        redirectFile = new File(currentDirectory.toFile(), "logFile.txt");
        /* Write to file */
        if (write_mode == 1) {
            try {
                buf = Files.newBufferedWriter(redirectFile.toPath(), StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
            } catch (IOException e) {
                System.err.println("Error Creating file: " + e.getMessage());
                return false;
            }
        }
        /* Append to file */
        if (write_mode == 2) {
            if (!redirectFile.exists()) {
                System.err.println("Error: File doesn't exist");
                return false;
            }
            try {
                buf = Files.newBufferedWriter(redirectFile.toPath(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.println("Error Creating file: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    /**
     * print a statement based of the write_mode
     *
     * @param s       statememnt to be printed
     * @param newLine takes two values:
     *                true: pritn new line at the end
     *                false don't print new line at the end
     * @author Adham Allam
     */
    public static void _print(String s, boolean newLine) {
        /* Print to screen */
        if (write_mode == 0) {
            System.out.print(s);
            if (newLine)
                System.out.println();
            return;
        }
        if (buf != null) {
            try {
                buf.write(s);
                if (newLine)
                    buf.newLine();
                buf.flush();
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
                return;
            }
        }
    }

    public static void _print(String s) {
        _print(s, true);
    }

    public static void main(String[] args) throws IOException {
        if (!initBuffer())
            buf = null;
        int NumberOfConnections;
        int NumberOfDevices;
        List<Device> devices = new ArrayList<>();
        try (Scanner input = new Scanner(System.in)) {
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
        }
        for (int i = 0; i < NumberOfDevices; i++) {
            devices.get(i).start();
        }
        buf.flush();
    }
}
