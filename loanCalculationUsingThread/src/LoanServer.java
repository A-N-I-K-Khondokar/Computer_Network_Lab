import java.io.*;
import java.net.*;

public class LoanServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Loan Server is listening on port 8000...");

        while (true) {
            Socket client = serverSocket.accept();
            System.out.println("New client connected: " + client.getInetAddress());

            LoanHandler handler = new LoanHandler(client);
            new Thread(handler).start();
        }
    }
}

class LoanHandler implements Runnable {
    private Socket clientSocket;

    public LoanHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            DataInputStream in = new DataInputStream(
                    clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(
                    clientSocket.getOutputStream());

            // read loan details sent by client
            double annualInterestRate = in.readDouble();
            int numberOfYears = in.readInt();
            double loanAmount = in.readDouble();

            System.out.println("Received -> Rate: " + annualInterestRate
                    + "%, Years: " + numberOfYears
                    + ", Amount: " + loanAmount);

            // calculate monthly payment
            double monthlyInterestRate = annualInterestRate / 1200;
            int numberOfPayments = numberOfYears * 12;

            double monthlyPayment = loanAmount * monthlyInterestRate /
                    (1 - Math.pow(1 / (1 + monthlyInterestRate), numberOfPayments));

            double totalPayment = monthlyPayment * numberOfPayments;

            // send results back to client
            out.writeDouble(monthlyPayment);
            out.writeDouble(totalPayment);
            out.flush();

            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}