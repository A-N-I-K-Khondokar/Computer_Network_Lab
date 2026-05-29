import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String[] args) {
        Socket clientSocket = null;

        try {
            clientSocket = new Socket("127.0.0.1", 6789);
            BufferedReader inFromUser = new BufferedReader(
                    new InputStreamReader(System.in));

            System.out.print("Enter a sentence: ");
            String sentence = inFromUser.readLine();

            PrintWriter outToServer = new PrintWriter(
                    clientSocket.getOutputStream(), true);

            BufferedReader inFromServer = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            outToServer.println(sentence);

            String modifiedSentence = inFromServer.readLine();

            if (modifiedSentence != null) {
                System.out.println("FROM SERVER: " + modifiedSentence);
            } else {
                System.out.println("No response received from server.");
            }

        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        } finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.out.println("Error closing socket: " + e.getMessage());
                }
            }
        }
    }
}