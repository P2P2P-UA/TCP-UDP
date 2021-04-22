import java.io.*;
import java.net.*;

public class Client {
    public void sendMsg() throws IOException {
        String buffer;
        String modifiedBuffer;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket(InetAddress.getLocalHost(), 6789);

        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer =
                new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        buffer = inFromUser.readLine();
        outToServer.writeBytes(buffer + '\n');
        modifiedBuffer = inFromServer.readLine();
        System.out.println("FROM SERVER: " + modifiedBuffer);
        clientSocket.close();

    }
}
