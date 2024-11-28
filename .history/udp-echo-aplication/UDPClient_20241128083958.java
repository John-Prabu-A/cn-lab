import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    public static void main(String args[]) throws IOException {
        // create client
        DatagramSocket client = new DatagramSocket();
        
        
        InetAddress addr = InetAddress.getLocalHost();
        byte[] buf = new byte[1024];
        int port = 4160;
        BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("[INFO] : Client connected\n[INFO] : Enter 'terminate' to end connection");
        while(true) {
            System.out.print("[SYSTEM] : > Enter message: ");
            String data = new String(din.readLine());
            buf = data.getBytes();
            // send data
            client.send(new DatagramPacket(buf, buf.length, addr, port));
            if(data.equalsIgnoreCase("terminate")) {
                break;
            }
        }
        client.close();
    }
}