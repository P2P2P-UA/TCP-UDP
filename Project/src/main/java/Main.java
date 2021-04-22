import java.io.IOException;
import java.sql.SQLOutput;

public class Main {
    private static Server serv;
    private static Client client;

    public static void main(String[] args)
    {
        Main main = new Main();
        System.out.println("\rSetting up server ...");
        serv = new Server();
        System.out.println("DONE\n");

        System.out.println("\rSetting up client ...");
        client = new Client();
        System.out.println("DONE\n");

        sendMsg();
    }
    public static void sendMsg() {
        while(true) {
            try {
                client.sendMsg();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
