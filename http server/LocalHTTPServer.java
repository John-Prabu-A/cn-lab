import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;

public class LocalHTTPServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // Handler for GET requests
        server.createContext("/get", exchange -> {
            String response = "<h1>Hello World!</h1>";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        server.createContext("/login", exchange -> {
            String userName = "admin";
            String password = "password";
            String response;
            if("POST".equals(exchange.getRequestMethod())) {
                BufferedReader in = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
                String requestData[] = in.readLine().trim().split(",");
                System.out.println("Request Body : " + requestData[0] + " " + requestData[1]);
                if(userName.equalsIgnoreCase(requestData[0]) && password.equalsIgnoreCase(requestData[1])) {
                    response = "Login Successful";
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                } else {
                    response = "InvalidCredentials!";
                    exchange.sendResponseHeaders(400, response.getBytes().length);
                }
                
                OutputStream out = exchange.getResponseBody();
                out.write(response.getBytes());
                out.close();

            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        });

        server.setExecutor(null);
        server.start();
        System.out.println("Server is running on http://localhost:8080");
    }
}
