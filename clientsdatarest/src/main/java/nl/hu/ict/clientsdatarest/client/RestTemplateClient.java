package nl.hu.ict.clientsdatarest.client;

import com.fasterxml.jackson.databind.JsonNode;
import nl.hu.ict.clientsdatarest.Student;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestTemplateClient {

    public static void main(String[] args) {
        RestTemplate rt = new RestTemplate();

        JsonNode respnse = rt.getForEntity("http://localhost:8080/students", JsonNode.class).getBody();
        for(JsonNode s: respnse.get("_embedded").get("students")){
            System.out.println(s.get("name").asText());
        }
    }
}
