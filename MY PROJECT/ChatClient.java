import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Scanner scanner;
    private String username;

    public ChatClient(String serverAddress, int port) {
        try {
            socket = new Socket(serverAddress, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            scanner = new Scanner(System.in);

            System.out.println("Connected to the chat server");

            // Enter username
            System.out.print("Enter your username: ");
            username = scanner.nextLine();
            out.println(username); // Send username to server

            // Thread to receive messages from the server
            new Thread(new ReceivedMessagesHandler()).start();

            // Send messages to the server
            sendMessage();

        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        }
    }

    private void sendMessage() {
        while (true) {
            System.out.print(username + ": ");
            String message = scanner.nextLine();
            out.println(message);
        }
    }

    private class ReceivedMessagesHandler implements Runnable {
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(message); // Display the broadcasted message
                }
            } catch (IOException e) {
                System.out.println("Connection closed by server.");
            }
        }
    }

    public static void main(String[] args) {
        new ChatClient("localhost", 1234); // Connect to the server on localhost and port 1234
    }
}
