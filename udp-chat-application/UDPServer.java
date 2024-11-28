import java.io.*;
import java.net.*;

public class UDPServer {

    public static void main(String args[]) throws IOException {
        DatagramSocket server = new DatagramSocket(4160);
        
        byte[] receiveBuf = new byte[1024];
        byte[] sendBuf = new byte[2024];

        DatagramPacket receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);
        DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length);

        BufferedReader din = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("[INFO] : Server Started");
        while(true) {
            server.receive(receivePacket);
            String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());
            if(msg.equalsIgnoreCase("terminate")) {
                System.out.println("[INFO] : Client terminated connection");
                break;
            }
            System.out.println("[CLIENT] : " + msg);
            String data = new String(din.readLine());
            sendBuf = data.getBytes();
            server.send(sendPacket);
            if(data.equalsIgnoreCase("terminate")) {
                System.out.println("[INFO] : Client connection terminated");
                break;
            }
            System.out.println("[SERVER] : " + data);
            
        }
        server.close();
    }
}