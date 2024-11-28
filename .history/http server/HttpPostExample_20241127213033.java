import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpPostExample {
    public static void main(String[] args) throws Exception {
        // define url and data
        String url = "http://localhost:8080";
        String data = "admin,password";

        // create new client
        HttpClient client = HttpClient.newHttpClient();

        // create request object with uri, post(body), header, build
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(url + "/login"))
            // .POST(HttpRequest.BodyPublishers.ofString(data))
            .header("Content-Type", "application/text")
            .build();
            
        // send request and get response    
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // printing response
        System.out.println("Response Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

        // make get request
        HttpRequest request2 = HttpRequest.newBuilder().uri(new URI(url + "/get")).GET().header("Content-type", "text/html").build();

        // send request
        HttpResponse<String> response2 = client.send(request2, HttpResponse.BodyHandlers.ofString());
        // print response
        System.out.println("code : " + response2.statusCode());
        System.out.println("Body : " + response2.body());
    }
}
