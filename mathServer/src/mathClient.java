import java.io.*;
import java.net.*;
public class mathClient {
    public static void main(String[] args) throws IOException {
        Socket client=new Socket("localhost",6000);

        BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out= new PrintWriter(client.getOutputStream(),true);

        System.out.println("Enter Your Expression here..: ");
        BufferedReader userInput=new BufferedReader(new InputStreamReader(System.in));
        String Expression=userInput.readLine();

        out.println(Expression);

        String solution=in.readLine();
        System.out.println(solution);

        client.close();
    }
}
