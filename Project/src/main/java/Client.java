import java.io.*;
import java.net.*;
class Client {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 6789)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            BufferedReader inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));
            String text;
            do {
                System.out.println("Enter text: ");
                text = inFromUser.readLine();
                writer.println(text);
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String time = reader.readLine();
                System.out.println(time);
            } while (!text.equals("bye"));
            socket.close();
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

}
