package nl.tomkemper.bep3.whutsupp;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Klas {
    @Id
    private String name;
    private List<Student> students;

    public String getName() {
        return name;
    }

    public List<Student> students() {
        return this.students;
    }

    protected Klas() {
    }

    public Klas(String naam) {
        this.name = naam;
        this.students = new ArrayList<>();
    }

    public Optional<Student> findStudent(long studentnr) {
        return this.students.stream().filter(s -> s.getStudentNr() == studentnr).findFirst();
    }
}
