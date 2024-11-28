import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

    public static void main(String args[]) throws IOException {
        DatagramSocket server = new DatagramSocket(4160);
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        System.out.println("[INFO] : Server Started");
        while(true) {
            server.receive(packet);
            String msg = new String(packet.getData(), 0, packet.getLength());
            if(msg.equalsIgnoreCase("terminate")) {
                System.out.println("[INFO] : Client terminated connection");
                break;
            }
            System.out.println("[CLIENT] : " + msg);
        }
        server.close();
    }
}