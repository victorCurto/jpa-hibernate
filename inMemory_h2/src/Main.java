import org.h2.tools.Server;

public class Main {

    public static void main(String[] args) {
        try {
            // Start H2 database server on port 9092 (change as needed)
            Server server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();

            // Print server status
            System.out.println("H2 Database Server is running and listening on port " + server.getPort());

            // To stop the server, you can call server.stop() when needed.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}