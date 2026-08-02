import java.io.*;
import java.net.*;
import java.util.Scanner;

public class LoanClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8000);

        DataOutputStream out = new DataOutputStream(
                socket.getOutputStream());
        DataInputStream in = new DataInputStream(
                socket.getInputStream());

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter annual interest rate (e.g. 5.5): ");
        double annualInterestRate = sc.nextDouble();

        System.out.print("Enter number of years: ");
        int numberOfYears = sc.nextInt();

        System.out.print("Enter loan amount: ");
        double loanAmount = sc.nextDouble();

        // send loan details to server
        out.writeDouble(annualInterestRate);
        out.writeInt(numberOfYears);
        out.writeDouble(loanAmount);
        out.flush();

        // receive results from server
        double monthlyPayment = in.readDouble();
        double totalPayment = in.readDouble();

        System.out.printf("Monthly Payment: %.2f%n", monthlyPayment);
        System.out.printf("Total Payment: %.2f%n", totalPayment);

        socket.close();
        sc.close();
    }
}