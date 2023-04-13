package nl.hu.ict.clientsdatarest;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    protected Student() {
    }

    public Student(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    private Staff slb;


    public Staff getSlb() {
        return slb;
    }

    public void setSlb(Staff slb) {
        this.slb = slb;
    }

    @OneToMany
    private List<Course> courses = new ArrayList<>();

    public List<Course> getCourses() {
        return courses;
    }
}
