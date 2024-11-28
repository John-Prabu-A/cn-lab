import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;

import java.net.ServerSocket;
import java.net.Socket;

public class TCPChatServer {
    public static void main(String args[]) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        Socket s = ss.accept();

        InputStreamReader socInp = new InputStreamReader(s.getInputStream());
        BufferedReader socIn = new BufferedReader(socInp);
        InputStreamReader sysInp = new InputStreamReader(System.in);
        BufferedReader sysIn = new BufferedReader(sysInp);

        PrintWriter socOut = new PrintWriter(s.getOutputStream());

        String req, res;
        while(true) {
            req = socIn.readLine();
            if(req.equalsIgnoreCase("terminate")) {
                System.out.println("[INFO] : Client Connection Terminated");
                break;
            } else 
                System.out.println("[CLIENT] : Message >> " + req );
            System.out.print("[SERVER] : Enter Message : ");
            res = sysIn.readLine();
            if(res.equalsIgnoreCase("terminate")) {
                System.out.println("[INFO] : Connection Terminating...");
                break;
            }
            socOut.println(res);
        }
        ss.close();
    }
}
