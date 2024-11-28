import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;


import java.net.Socket;



public class TCPChatClient {
    public static void main(String args[]) throws IOException {
        Socket s = new Socket("localhost", 8080);
        System.out.println("[INFO] : Server Connection Established");
        InputStreamReader socInp = new InputStreamReader(s.getInputStream());
        BufferedReader socIn = new BufferedReader(socInp);
        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);

        InputStreamReader sysInp = new InputStreamReader(System.in);
        BufferedReader sysIn = new BufferedReader(sysInp);
        
        String send, received;
        while(true) {
            System.out.print("[CLIENT] MESSAGE : >> ");
            send = sysIn.readLine();
            if(send.equalsIgnoreCase("terminate")) {
                System.out.println("[INFO] : Disconnecting...\n[INFO] : Server Connection Terminated");
                break;
            }
            pr.println(send);
            received = socIn.readLine();
            if(received.equalsIgnoreCase("terminate")) {
                System.out.println("[INFO] : Server Connection Terminated");
                break;
            }
            System.out.println("[SERVER] MESSAGE : >> " + received);
        }

        s.close();
    }    
}
