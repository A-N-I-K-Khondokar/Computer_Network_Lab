import java.util.Scanner;

public class Problem02 {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);

        /* Sender side */
        // Ask how many frames the user wants to send
        System.out.println("Enter the number of frames you want to send: ");
        int numberOfFrames=scanner.nextInt();

        // Read each frame and store them in an array
        String[] frames= new String[numberOfFrames];
        for(int i=0; i<numberOfFrames; i++){
            System.out.println("Enter frame "+(i+1)+" : ");
            frames[i]=scanner.next();
        }

        /* Build the transmitted string
        *  Transmitted frames= frameSize+frame */
        String transmittedFrame="";
        for(int i=0; i<numberOfFrames; i++){
            int frameSize=frames[i].length()+1;
            transmittedFrame+=frameSize + frames[i];
        }

        System.out.println("Transmitted frame is : "+transmittedFrame);

        /* Receiver side*/
        System.out.print("\nEnter the received data: ");
        String received = scanner.next();

        // Decoding the received data
        int position=0;
        int frameNumber=1;

        while (position < received.length()){
            int size=Character.getNumericValue(received.charAt(position));
            int frameStart=position+1;
            int frameLength=size-1;

            String frameData=received.substring(frameStart,(frameStart+frameLength));
            System.out.println("Frame "+frameNumber+" :"+frameData);

            position+=size;
            frameNumber++;

        }
    }
}
