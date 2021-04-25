import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPThread extends Thread {
    private DatagramSocket serverSocket;
    private int clientID;

    public UDPThread(DatagramSocket datagramSocket, int clientID) {
        this.serverSocket = datagramSocket;
        this.clientID = clientID;
    }

    public void run() {
        byte[] receivingDataBuffer = new byte[1024];
        byte[] sendingDataBuffer = new byte[1024];

        DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length); // store received data from client
        // Receive data from the client and store in inputPacket
        try {
            serverSocket.receive(inputPacket);
            String receivedData;
            do {
                // Printing out the client sent data
                receivedData = new String(inputPacket.getData()); // the data is in bytes--> String from bytes
                String response = "Server received string from UDPClient: " + receivedData;
                sendingDataBuffer = response.getBytes();

                // client's IP address and the port
                InetAddress senderAddress = inputPacket.getAddress();
                int senderPort = inputPacket.getPort();

                // Create new UDP packet with data to send to the client
                DatagramPacket outputPacket = new DatagramPacket(sendingDataBuffer,
                        sendingDataBuffer.length,
                        senderAddress,
                        senderPort);

                // Send the created packet to client
                serverSocket.send(outputPacket);
            }while (!receivedData.equals("bye"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}