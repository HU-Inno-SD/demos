package nl.hu.ict.inno.hellogateways.students;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Course> completed = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Course> enrolled = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    protected Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public List<Course> getCompleted() {
        return Collections.unmodifiableList(completed);
    }

    public List<Course> getEnrolled() {
        return Collections.unmodifiableList(enrolled);
    }

    public void enroll(Course c) {
        //TODO: Checks, bijv. of ie niet al completed is, of de prereqs ok zijn, etc. etc.
        this.enrolled.add(c);
    }

    public void complete(Course c) {
        this.completed.add(c);
    }
}
