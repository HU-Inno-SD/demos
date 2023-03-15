package nl.hu.ict.inno.hellogateways.students;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    private final EntityManager entities;

    public StudentController(EntityManager entities) {
        this.entities = entities;
    }

    private Student findStudent(Long id) {
        Student student = entities.createQuery("select s from Student s where s.id = ?1", Student.class)
                .setParameter(1, id)
                .getSingleResult();

        if (student == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Student not found");
        }

        return student;
    }

    @GetMapping
    public List<StudentDTO> getAll() {
        List<Student> students = entities.createQuery("select s from Student s", Student.class)
                .getResultList();
        return students.stream().map(StudentDTO::fromStudent).toList();
    }

    @GetMapping("{id}")
    public StudentDTO getOne(@PathVariable("id") Long id) {
        Student student = findStudent(id);
        return StudentDTO.fromStudent(student);
    }

    @GetMapping("{id}/enrollments")
    public List<CourseDTO> getEnrollments(@PathVariable("id") Long id) {
        Student student = findStudent(id);
        return student.getEnrolled().stream().map(CourseDTO::fromCourse).toList();
    }

    @GetMapping("{id}/completed")
    public List<CourseDTO> getCompletedCourses(@PathVariable("id") Long id) {
        Student student = findStudent(id);
        return student.getCompleted().stream().map(CourseDTO::fromCourse).toList();
    }

    public record StudentDTO(Long id, String name) {
        public static StudentDTO fromStudent(Student student) {
            return new StudentDTO(student.getId(), student.getName());
        }
    }

    public record CourseDTO(String code, String name) {
        public static CourseDTO fromCourse(Course course) {
            return new CourseDTO(course.getCode(), course.getName());
        }
    }
}
