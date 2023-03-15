package nl.hu.ict.inno.hellogateways.courses;

import jakarta.persistence.*;

import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private long id;

    public CourseId getId() {
        return new CourseId(id);
    }

    private String name;

    public String getName() {
        return this.name;
    }

    @Column(unique = true)
    private String code;

    public String getCode() {
        return this.code;
    }

    private int credits;

    public ECTS getCredits() {
        return ECTS.from_database(this.credits);
    }

    @Enumerated(EnumType.STRING)
    private CourseLevel level;

    public CourseLevel getLevel() {
        return this.level;
    }

    protected Course() {
        //For JPA/Hibernate
    }

    public Course(String code, String name, CourseLevel level) {
        this(code, name, level, ECTS.course(), new ArrayList<>());
    }

    public Course(String code, String name, CourseLevel level, List<Course> prereqs) {
        this(code, name, level, ECTS.course(), prereqs);
    }

    public Course(String code, String name, CourseLevel level, ECTS credits, List<Course> prereqs) {
        Objects.requireNonNull(code);
        Objects.requireNonNull(name);
        Objects.requireNonNull(prereqs);

        this.name = name;
        this.code = code;
        this.credits = credits.value();
        this.level = level;
        this.prerequisites = new HashSet<>(prereqs);
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Course> prerequisites = new HashSet<>();

    public Set<Course> getDirectPrerequisites() {
        return Collections.unmodifiableSet(this.prerequisites);
    }

    public Set<Course> getAllPrerequisites() {
        Set<Course> allPrereqs = new HashSet<>(this.prerequisites);

        Set<Course> transitives = this.prerequisites.stream()
                .flatMap(c -> c.getAllPrerequisites().stream())
                .collect(Collectors.toSet());

        allPrereqs.addAll(transitives);

        return allPrereqs;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && credits == course.credits && Objects.equals(name, course.name) && code.equals(course.code) && level == course.level && Objects.equals(prerequisites, course.prerequisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, credits, level, prerequisites);
    }
}
