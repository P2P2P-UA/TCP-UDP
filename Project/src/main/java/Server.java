import java.io.*;
import java.net.*;

public class Server {

    public static void main(String argv[]) throws Exception
    {
        String clientBuf;
        String modifiedBuf;

        ServerSocket welcomeSocket = new ServerSocket(6789);

        while(true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            DataOutputStream  outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());

            clientBuf = inFromClient.readLine();

            modifiedBuf = clientBuf.toUpperCase() + '\n';

            outToClient.writeBytes(modifiedBuf);
        }
    }
}