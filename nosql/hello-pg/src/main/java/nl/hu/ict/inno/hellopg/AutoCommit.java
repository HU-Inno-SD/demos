package nl.hu.ict.inno.hellopg;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

//@Component
public class AutoCommit implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String url = "jdbc:postgresql://localhost/fabriek?user=postgres&password=1q2w3e!";
        Connection conn = DriverManager.getConnection(url);

        Statement s = conn.createStatement();
        s.execute("delete from besteldartikel ba where ba.bestnr = 789;\n" +
                "delete from bestelling b where b.bestnr = 789;");

        System.out.println("Oude meuk pleite, druk op een toets om verder te gaan");
        scanner.nextLine();

        s.execute("insert into bestelling(bestnr, klantnr, fabnr, datum)\n" +
                "values(789, 121, 124, current_date);");

        s.execute("insert into besteldartikel(bestnr, artnr, aantal, bestelprijs)\n" +
                "values(789, 121, 100, 1.50);");

        System.out.println("Halverwege, druk op een toets om verder te gaan");
        scanner.nextLine();

        s.execute("insert into besteldartikel(bestnr, artnr, aantal, bestelprijs)\n" +
                "values(789, 122, 2, 2.50);");
    }
}
