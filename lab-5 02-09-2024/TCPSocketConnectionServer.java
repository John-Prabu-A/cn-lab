import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPSocketConnectionServer {
    public static void main(String args[]) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        Socket s = ss.accept();
        System.out.println("[INFO] : Client Connected");
        InputStreamReader socIn = new InputStreamReader(s.getInputStream());
        BufferedReader in = new BufferedReader(socIn);
        String x;
        while(true) {
            x = in.readLine();
            System.out.println("[CLIENT] : " + x);
            if(x.equalsIgnoreCase("terminate")) {
                System.out.println("[INFO] : Client Terminated Connection");
                break;
            }
        }
        ss.close();
    }
}

