import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPSocketConnectionClient {
    public static void main(String args[]) throws IOException {
        Socket s = new Socket("localhost", 8080);
        System.out.println("[INFO] : Server connection established");
        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);
        InputStreamReader sysIn = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(sysIn);
        String y;
        while(true) {
            System.out.print("[SYSTEM] > Enter Messge : ");
            y = in.readLine();
            if(y.equalsIgnoreCase("terminate")) {
                System.out.println("[INFO] : Connection Terminated");
                pr.println("terminate");
                break;
            } else {
                pr.println(y);
            }
        }
        s.close();
    }    
}
