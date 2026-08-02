import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) throws Exception {
        ServerSocket server=new ServerSocket(5001);
        System.out.println("Server is waiting......");

        Socket client= server.accept();
        System.out.println("Client is connected!");

        BufferedReader in= new BufferedReader(
                new InputStreamReader(client.getInputStream())
        );

        PrintWriter out= new PrintWriter(client.getOutputStream(),true);
        out.println("hello from server");

        String message=in.readLine();
        System.out.println("Message form client :" + message);

        client.close();
        server.close();

    }
}
