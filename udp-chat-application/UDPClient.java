import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    public static void main(String args[]) throws IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress addr = InetAddress.getLocalHost();
        byte[] sendBuffer = new byte[1024];
        byte[] receiveBuffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        int port = 4160;
        BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("[INFO] : Client connected\n[INFO] : Enter 'terminate' to end connection");
        while(true) {
            System.out.print("[SYSTEM] : > Enter message: ");
            String data = new String(din.readLine());
            sendBuffer = data.getBytes();
            client.send(new DatagramPacket(sendBuffer, sendBuffer.length, addr, port));
            if(data.equalsIgnoreCase("terminate")) {
                break;
            }
            client.receive(receivePacket);
            String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());
            if(msg.equalsIgnoreCase("terminate")) {
                System.out.println("[INFO] : Server terminated connection");
                break;
            }
            System.out.println("[SERVER] : " + msg);
        }
        client.close();
    }
}