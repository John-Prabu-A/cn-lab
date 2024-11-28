import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class LocalFTPClient {
    public static void main(String[] args) throws Exception {
        // create client
        HttpClient client = HttpClient.newHttpClient();

        // send request
        HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI("http://127.0.0.1:8080/upload"))
        .POST(HttpRequest.BodyPublishers.ofFile(Path.of("J:\\OneDrive\\Desktop\\cn-lab-study\\ftp server\\file.txt")))
        .build();

        // get response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // print response
        System.out.println("Res Code   : " + response.statusCode());
        System.out.println("Res Body : " + response.body());
    }
}
