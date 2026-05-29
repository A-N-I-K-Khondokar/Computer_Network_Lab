import java.io.*;
import java.net.*;

class TCPServer {
    public static void main(String[] args) {
        ServerSocket welcomeSocket = null;

        try {
            welcomeSocket = new ServerSocket(6789);
            System.out.println("Server is running and waiting for connections...");

            while (true) {
                Socket connectionSocket = null;

                try {
                    connectionSocket = welcomeSocket.accept();
                    System.out.println("Client connected: " +
                            connectionSocket.getInetAddress());

                    BufferedReader inFromClient = new BufferedReader(
                            new InputStreamReader(connectionSocket.getInputStream()));

                    PrintWriter outToClient = new PrintWriter(
                            connectionSocket.getOutputStream(), true);

                    String clientSentence = inFromClient.readLine();

                    if (clientSentence != null) {
                        String capitalizedSentence = clientSentence.toUpperCase();
                        outToClient.println(capitalizedSentence);
                        System.out.println("Received: " + clientSentence);
                        System.out.println("Sent back: " + capitalizedSentence);
                    }

                } catch (IOException e) {
                    System.out.println("Error handling client: " + e.getMessage());
                } finally {
                    if (connectionSocket != null) {
                        try {
                            connectionSocket.close();
                        } catch (IOException e) {
                            System.out.println("Error closing connection: " +
                                    e.getMessage());
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        } finally {
            if (welcomeSocket != null) {
                try {
                    welcomeSocket.close();
                } catch (IOException e) {
                    System.out.println("Error closing server socket: " +
                            e.getMessage());
                }
            }
        }
    }
}