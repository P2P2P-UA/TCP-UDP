import java.io.*;
import java.net.*;
class Client {

    public static void main(String[] args) throws Exception {
        String sentence;
        String modifiedSentence;

        System.out.println("Enter the name of the file you would like to transfer: ");
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 6789);

        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer =
                new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: " + modifiedSentence);

        if (modifiedSentence.equals("FILE EXISTS"))
        {
            InputStream is = clientSocket.getInputStream();
            FileOutputStream fr = new FileOutputStream("D:\\SEM6\\Distributed\\FT test\\test.txt");
            byte size[] = new byte[5000];
            is.read(size,0,size.length);
            fr.write(size, 0,size.length);
        }
        else System.out.println("Error: no such file found");
        clientSocket.close();

    }
}