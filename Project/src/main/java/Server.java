import java.io.*;
import java.net.*;
import java.util.ArrayList;

class Server {
    private static String fileInput = null;
    public static void main(String[] args) throws Exception
    {
        String clientSentence;
        String capitalizedSentence;
        ArrayList<String> availFiles = new ArrayList<>();
        availFiles.add("FTP.txt");

        FileInputStream file = new FileInputStream("D:\\Documents\\FTP.txt");

        ServerSocket welcomeSocket = new ServerSocket(6789);
        Socket connectionSocket = welcomeSocket.accept();

        BufferedReader inFromClient =
                new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream  outToClient =
                new DataOutputStream(connectionSocket.getOutputStream());
        clientSentence = inFromClient.readLine();

        if(clientSentence.equals(availFiles.get(0))) {
            outToClient.writeBytes("FILE EXISTS\n");
            byte size[] = new byte[5000];
            file.read(size,0,size.length); // read file from start to end
            OutputStream os = connectionSocket.getOutputStream();
            os.write(size,0,size.length);


//            fileInput = "C:\\Users\\malon\\Desktop\\" + clientSentence;
//            capitalizedSentence = clientSentence.toUpperCase() + '\n';
//            outToClient.writeBytes(capitalizedSentence);
        }
    }
}