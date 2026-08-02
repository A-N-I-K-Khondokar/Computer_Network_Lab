import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client {
    public static void main(String[] args) throws IOException {
        Socket client=new Socket("localhost",1234);
        BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out=new PrintWriter(client.getOutputStream(),true);

        String line="";
        Scanner sc=new Scanner(System.in);

        while (!line.equalsIgnoreCase("Exit")){
            System.out.println("You ");
            line=sc.nextLine();
            out.println(line);

            if(!line.equalsIgnoreCase("Exit")){
                System.out.println("Server response : "+in.readLine());
            }
        }
        client.close();
    }
}
