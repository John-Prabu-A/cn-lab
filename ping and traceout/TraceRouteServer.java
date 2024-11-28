import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TraceRouteServer {
    public static void main(String[] args) throws Exception {
        String req = "", res = "";
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
            ProcessBuilder pb = new ProcessBuilder("ping", req);
            Process p = pb.start();

            BufferedReader processIn = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while((res = processIn.readLine()) != null) {
                out.println(res);
                out.flush();
            }
            out.println("END");
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            s.close();
            ss.close();
        }
    }
}
