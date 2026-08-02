import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("localhost",5001);

        PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
        out.println("Hello form client!");

        BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String rec=in.readLine();
        System.out.println("Message form server "+rec);
        socket.close();



    }
}
