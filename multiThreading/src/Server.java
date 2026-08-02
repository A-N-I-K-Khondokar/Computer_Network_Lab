import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(1234);
        System.out.println("Server is waiting.....");

        while (true){
            Socket client=server.accept();
            System.out.println("This client is connected "+client.getInetAddress());

            ClientHandler handler=new ClientHandler(client);
            new Thread(handler).start();
        }
    }
}

class ClientHandler implements Runnable{
    private Socket clientSocket;

    public ClientHandler(Socket socket){
        this.clientSocket=socket;
    }
    public void run(){
        try{
            BufferedReader in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out=new PrintWriter(clientSocket.getOutputStream(),true);

            String message;
            while ((message=in.readLine()) !=null){
                System.out.println(message);
                out.println(message);
            }
            clientSocket.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}