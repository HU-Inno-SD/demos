package nl.hu.ict.inno.hellogateways.students;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Course {
    @Id
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    protected Course(){}

    public Course(String code, String name) {
        Objects.requireNonNull(code);
        Objects.requireNonNull(name);

        this.code = code;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return code.equals(course.code) && name.equals(course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }
}
