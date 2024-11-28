import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TestDNSClient {
    public static void main(String[] args) throws Exception {
        // get ipaddress or host name from user
        String req;
        String res;
        System.out.print("Enter ip or host name : ");
        Scanner scanner = new Scanner(System.in);
        req = scanner.nextLine();
        
        // create socket with port 9999
        Socket s = new Socket("localhost", 9999);
        try {

            // get input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream());

            // send data to server
            out.println(req);
            out.flush();
            // receive response from server
            res = in.readLine();
            // show to client
            System.out.println("Ping Response : " + res);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
            s.close();
        }
    }
}