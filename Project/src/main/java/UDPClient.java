import java.io.Console;
import java.io.IOException;
import java.net.*;

public class UDPClient {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) return;
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        try {
            DatagramSocket clientSocket = new DatagramSocket();

            // Get the IP address of the server
            InetAddress IPAddress = InetAddress.getByName(hostname);

            // Creating corresponding buffers
            byte[] sendingDataBuffer = new byte[1024];
            byte[] receivingDataBuffer = new byte[1024];
            String text;
            Console console = System.console();
            /* Converting data to bytes and
            storing them in the sending buffer */
            System.out.println("Client connected");
            do {
                text = console.readLine("Enter text: "); // ask user to enter a string
                if(!text.equals("bye")) { // client will remember last message received from server, not good when disconnected
                    sendingDataBuffer = text.getBytes();

                    // Creating a UDP packet
                    DatagramPacket outputPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length, IPAddress, port);

                    // sending UDP packet to the server
                    clientSocket.send(outputPacket);

                    // Get the server response
                    DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
                    clientSocket.receive(inputPacket);

                    // Printing the received data
                    String receivedData = new String(inputPacket.getData());
                    System.out.println(receivedData);
                }
                else{
                    System.out.println("Client left the server");
                }
            }while (!text.equals("bye"));
            // Closing the socket connection with the server
            clientSocket.close();


        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

}