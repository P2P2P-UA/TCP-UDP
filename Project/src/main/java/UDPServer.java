import java.io.IOException;
import java.net.DatagramSocket;

public class UDPServer {
    private int port;
    private DatagramSocket serverSocket;

    public UDPServer(int port){
        this.port=port;
    }

    public void start() {
        try{
            this.serverSocket= new DatagramSocket(this.port);
            System.out.println("Multi-thread UDP Server is now on port " + this.port);
            int clientsConnected=0;

            while(true){
                new UDPThread(serverSocket, clientsConnected).start();
            }
        }catch(
                IOException ex){
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public void close() {
        this.serverSocket.close();
    }
}