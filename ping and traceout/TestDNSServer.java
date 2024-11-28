import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TestDNSServer {
    public static void main(String args[]) throws Exception {
        String req = "", res = "";
        String ip[] = { "192.168.1.1", "192.168.1.2" };
        String host[] = { "sample1.com", "sample2.com"};

        // create server socket for port 9999
        ServerSocket ss = new ServerSocket(9999);

        // accept client
        Socket s = ss.accept();

        try {
                // define input and output stream
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            // receive data
            req = in.readLine();

            // process data
            for(int i = 0; i < ip.length; i++) {
                if(ip[i].equals(req)) {
                    res = host[i];
                    break;
                } else if(host[i].equals(req)) {
                    res = ip[i];
                    break;
                }
            }

            // send data
            if(res.isEmpty()) {
                out.println("Invalid IP or HOST name!");
                out.flush();
            } else if(res.charAt(3) == '.') {
                out.println(req + " resolved as " + res);
                out.flush();
            } else {
                out.println(req + " resolved as " + res);
                out.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            s.close();
            ss.close();
        }

    }
}
