package helloeventsourcing;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class HelloJava implements CommandLineRunner {

    private SomeEntityRepository someEntitites;

    public static void main(String[] args) {
        SpringApplication.run(HelloJava.class, args);
    }

    public HelloJava(SomeEntityRepository someEntitites){
        this.someEntitites = someEntitites;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("uuuuuurgh");
        SomeEntity babayaga = new SomeEntity("Baby Yaga", "baba@example.com");
        babayaga.changeName("Baba Yaga");
        babayaga.changeContactDetails(null, "06-12345678");

        this.someEntitites.save(babayaga);

        SomeEntity babarebuilt = this.someEntitites.get(babayaga.getId());
        System.out.println(babarebuilt.getPhonenr());

        babarebuilt.changeContactDetails("babayaga@gmail.com", null);

        this.someEntitites.save(babarebuilt);

    }
}
