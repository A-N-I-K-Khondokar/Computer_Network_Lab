import java.io.*;
import java.net.*;
import java.util.StringTokenizer;


public class MathServer {
    public static void main(String[] args) throws IOException {
        ServerSocket mathServer=new ServerSocket(6000);
        System.out.println("Math server is open......");

        Socket client=mathServer.accept();
        System.out.println("Client is connected");

        BufferedReader in =new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out= new PrintWriter(client.getOutputStream(),true);

        String formClient=in.readLine();
        System.out.println("From raw Expression : "+formClient);

        StringTokenizer st=new StringTokenizer(formClient);
        int num1=Integer.parseInt(st.nextToken());
        String operator= st.nextToken();
        int num2=Integer.parseInt(st.nextToken());

        int result= switch (operator){
            case "+"->num1+num2;
            case "-"->num1-num2;
            case "*"->num1*num2;
            case "/"->num1/num2;
            default -> 0;
        };

        out.println("Result from the server : "+ result);

        client.close();
        mathServer.close();
    }
}
