import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private ServerSocket serverSocket;
    private Set<ClientHandler> clientHandlers = new HashSet<>();

    public ChatServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clientHandlers.add(clientHandler);
                clientHandler.start();
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }

    public void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler client : clientHandlers) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public void removeClient(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
        System.out.println("Client disconnected.");
    }

    public static void main(String[] args) {
        new ChatServer(1234); // Initialize the server on port 1234
    }

    class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private ChatServer server;
        private String username;

        public ClientHandler(Socket socket, ChatServer server) {
            this.socket = socket;
            this.server = server;

            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Ask the client for a username
                out.println("Enter your username: ");
                this.username = in.readLine();
                System.out.println(username + " has joined the chat.");
                broadcastMessage(username + " has joined the chat.", this);

            } catch (IOException e) {
                System.out.println("Error setting up client streams: " + e.getMessage());
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }

        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(username + ": " + message);
                    server.broadcastMessage(username + ": " + message, this);
                }
            } catch (IOException e) {
                System.out.println("Connection closed with " + username);
            } finally {
                server.removeClient(this);
                broadcastMessage(username + " has left the chat.", this);
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error closing socket: " + e.getMessage());
                }
            }
        }
    }
}
