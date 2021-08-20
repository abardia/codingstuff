//import java.util.Scanner;
import java.net.*;

public class Scanner{
    public static void main(String[] args){

        java.util.Scanner s = new java.util.Scanner(System.in);

        for(int i = 0; i < 50; i++) System.out.print("-");
        System.out.println("\n Welcome! Use Scanner now!");
        for(int i = 0; i < 50; i++) System.out.print("-");

        Socket socket = new Socket();
        Boolean connectable = false;
        int timeout = 2000;
        
        int count = 0;

        while(true){
            if(count == 0)
                System.out.print("\n Please give me your IP-Adress you want so scan: ");
            else 
                System.out.print(" Please give me your IP-Adress you want so scan: ");
                
            String adress = s.nextLine();
            if(adress.isBlank()){
                System.out.println(" You did not write a legit IP!");
                System.exit(0);
            }

            System.out.print(" Now give me the port you want to scan: ");
            String port = s.nextLine();
            if(port.isBlank()){
                System.out.println(" You did not write a legit Port!");
                System.exit(0);
            }

            System.out.println("\n Scanning IP: "+ adress + " and port: " + port);
            SocketAddress socketAddress = null;
            try{
                socketAddress = new InetSocketAddress(adress, Integer.parseInt(port));
            }
            catch(Exception e){
                System.out.println(" Your port is not valid!");
                System.exit(0);
            }

            try{
                socket.connect(socketAddress,timeout);
                socket.close();
                connectable = true;
            } catch(Exception e){
                connectable = false;
            }
            System.out.println(" Socket is connected?: " + connectable);

            System.out.print(" Type n for new-Search: ");

            if(s.nextLine().equals("n") == false){
                System.out.println(" Thank you!");
                s.close();
                return;
            }
            count++;
        }
    }
}
