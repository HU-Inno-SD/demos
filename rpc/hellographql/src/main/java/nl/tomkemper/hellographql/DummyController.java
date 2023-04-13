package nl.tomkemper.hellographql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DummyController {

    @QueryMapping
    public String hello() {
        return "Hello World";
    }

    @QueryMapping
    public String bye() {
        return "Bye World";
    }

    @QueryMapping
    public List<Integer> numbers(@Argument int max) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            result.add(i);
        }

        return result;
    }

    @QueryMapping
    public List<Person> persons() {
        return List.of(
                new Person("Tom", 39),
                new Person("Suzan", 39)
        );
    }

    @SchemaMapping(typeName = "Person", field = "quote")
    public String quote(Person p){
        return "Dit is een quote van " + p.name();
    }
}
