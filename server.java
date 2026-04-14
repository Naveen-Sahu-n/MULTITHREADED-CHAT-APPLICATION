import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class server {

    private static Set<ClientHandler> clientHandlers = new HashSet<>();
    private static int port=12345;
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandlers.add(clientHandler);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler client : clientHandlers) {
            if (client != sender) {
                client.sendMessage( "["+sender.username+"] : "+message);
            }
        }
    }

    public static void removeClient(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
        System.out.println("Client disconnected: " + clientHandler.clientSocket.getInetAddress());
    }


    static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader reader;
        private PrintWriter writer;
        private String username;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            try {
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writer = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMessage(String message) {
            writer.println(message);
        }

        @Override
        public void run() {
            String message;
            try {
                username = reader.readLine();
                System.out.println("User " + username + " has connected.");
                writer.println("Welcome to the chat, " + username + "!");
                while ((message = reader.readLine()) != null) {
                    if ("exit".equalsIgnoreCase(message)||"disconnect".equalsIgnoreCase(message) ) {
                        break;  
                    }
                    System.out.println("["+username+"] : " + message);
                    broadcastMessage(message, this);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                removeClient(this);
            }
        }
    }
}
