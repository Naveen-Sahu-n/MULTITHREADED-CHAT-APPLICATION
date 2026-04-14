import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(hostname, port)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter your username: ");
            String username = consoleReader.readLine(); 
            writer.println(username);
            Thread readThread = new Thread(() -> {
                String serverMessage;
                try {
                    while ((serverMessage = reader.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    if (!socket.isClosed()) {
                          e.printStackTrace();
                    }
                }
            });
            readThread.start();

            String message;
            while ((message = consoleReader.readLine()) != null) {
                 if ("exit".equalsIgnoreCase(message) ||"disconnect".equalsIgnoreCase(message)  ) {
                    writer.println("exit client disconnected"); 
                    break; 
                }
                writer.println(message);
                
            }
            

        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}