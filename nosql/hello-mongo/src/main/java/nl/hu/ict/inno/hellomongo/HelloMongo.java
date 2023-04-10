package nl.hu.ict.inno.hellomongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@SpringBootApplication
public class HelloMongo {

    public static void main(String[] args) {

        MongoClient client = MongoClients.create("mongodb://localhost");


        MongoOperations mongoOps = new MongoTemplate(client, "database");
        mongoOps.insert(new SomeEntity("Test"));

        System.out.println(mongoOps.findOne(new Query(where("name").is("Test")), SomeEntity.class));

        mongoOps.dropCollection("someEntity");

        SpringApplication.run(HelloMongo.class, args);
    }
}
