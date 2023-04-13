package nl.hu.ict.clientsdatarest.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpNetClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .build();

        HttpRequest req = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/students"))
                .build();

        HttpResponse<String> resp =  client.send(req, HttpResponse.BodyHandlers.ofString());
        JsonNode respJson = new ObjectMapper().readTree(resp.body());

        for(JsonNode s: respJson.get("_embedded").get("students")){
            System.out.println(s.get("name").asText());
        }
    }
}
