package nl.tomkemper.bep3.whutsupp;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import nl.tomkemper.bep3.whutsupp.startup.MongoInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class Whutsupp {
    public static final boolean TEST_DATA = true;
    public static final String MONGODB_NAME = "whutsupp";
    public static final String INCOMING_EXCHANGE = "whutsupp.incoming";
    public static final String INCOMING_QUEUE = INCOMING_EXCHANGE;

    public static final String OUTGOING_EXCHANGE = "whutsupp.outgoing";
    public static final String OUTGOING_QUEUE = OUTGOING_EXCHANGE;

    public static final String PM_EXCHANGE = "whutsupp.pm";
    public static final String ANNOUNCE_EXCHANGE = "whutsupp.announce";

    public static void main(String[] args) throws Exception {
        if (TEST_DATA) {
            MongoClient client = MongoClients.create();
            MongoTemplate template = new MongoTemplate(client, MONGODB_NAME);
            new MongoInit(template).run(args);
        }

        SpringApplication.run(Whutsupp.class, args);
    }
}
