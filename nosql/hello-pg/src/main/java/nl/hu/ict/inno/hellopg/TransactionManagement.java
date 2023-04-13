package nl.hu.ict.inno.hellopg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

@Component
public class TransactionManagement implements CommandLineRunner {
    public TransactionManagement(JdbcTemplate template){
        this.jdbcTemplate = template;
    }
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional //Halverwege de database resetten laat het nut van transactional zien. En ook Isolation. Dus de A & I van ACID
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        jdbcTemplate.execute("delete from besteldartikel ba where ba.bestnr = 789;\n" +
                "delete from bestelling b where b.bestnr = 789;");

        System.out.println("Oude meuk pleite, druk op een toets om verder te gaan");
        scanner.nextLine();

        jdbcTemplate.execute("insert into bestelling(bestnr, klantnr, fabnr, datum)\n" +
                "values(789, 121, 124, current_date);");

        jdbcTemplate.execute("insert into besteldartikel(bestnr, artnr, aantal, bestelprijs)\n" +
                "values(789, 122, 100, 1.50);");

        System.out.println("Halverwege");
        scanner.nextLine();

        jdbcTemplate.execute("insert into besteldartikel(bestnr, artnr, aantal, bestelprijs)\n" +
                "values(789, 121, 2, 2.50);");
    }
}
