import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Path;

import com.sun.net.httpserver.*;

public class LocalFTPServer {
    public static void main(String[] args) throws Exception{
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/upload", exchange -> {
            if("POST".equals(exchange.getRequestMethod())) {
                InputStream in = exchange.getRequestBody();
                Files.copy(in, Path.of("recieved.txt"));
                exchange.sendResponseHeaders(200, "file received".getBytes().length);
                exchange.getResponseBody().write("file received".getBytes());
                exchange.close();
            } else {
                exchange.sendResponseHeaders(405, -1); 
            }
        });

        server.setExecutor(null);
        server.start();
        System.out.println("HTTP Server started on ftp://127.0.0.1:21/upload");
    }
}
