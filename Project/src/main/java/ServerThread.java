import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{

    private Socket socket;
    public ServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try{
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            String text;
            do {
                text = reader.readLine();
                writer.println(text.toString());

            } while (!text.equals("exit"));

            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
