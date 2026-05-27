import java.util.Scanner;
public class Problem01 {
    public static void main(String[] args) {

        //The scanner for taking input from the user.
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the binary strings:");
        String data=scanner.next();


        System.out.println("Enter the choice:");
        System.out.println("(1 for even parity and 2 for odd parity)");
        int choice=scanner.nextInt();

        //Count the number of '1' in the string.
        int count=0;
        for(int i=0; i<data.length(); i++){
            if( data.charAt(i)=='1'){
                count++;
            }
        }

        //Generate a parity string
        String result;

        if(choice==1){
            if(count%2==0){
                result=data+'0';
            }else {
                result=data+'1';
            }
        }else {
            if(count%2!=0){
                result=data+'0';
            }else {
                result=data+'1';
            }
        }

        //Output:
        System.out.println("The String after adding parity bits is : "+result);
    }
}
