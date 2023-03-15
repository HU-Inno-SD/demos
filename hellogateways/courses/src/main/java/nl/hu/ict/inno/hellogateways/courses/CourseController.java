package nl.hu.ict.inno.hellogateways.courses;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("courses")
@Transactional
public class CourseController {

    private final EntityManager entities;

    public CourseController(EntityManager entities) {
        this.entities = entities;
    }

    @GetMapping
    public List<CourseDTO> getAll(){
        List<Course> allCourses = entities.createQuery("select c from Course c", Course.class).getResultList();

        return allCourses.stream().map(CourseDTO::fromCourse).toList();
    }

    @GetMapping("{code}")
    public CourseDTO findByCode(@PathVariable("code") String code){
        Course c = entities.createQuery("select c from Course c where c.code = ?1", Course.class)
                .setParameter(1, code)
                .getSingleResult();

        if(c == null){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Course not found");
        }

        return CourseDTO.fromCourse(c);
    }


    public record TinyCourseDTO(String code, String name){
        public static TinyCourseDTO fromCourse(Course course) {
            return new TinyCourseDTO(course.getCode(), course.getName());
        }
    }

    public record CourseDTO(String code, String name, List<TinyCourseDTO> prereqs){
        public static CourseDTO fromCourse(Course course) {
            List<TinyCourseDTO> directPrereqs = course.getDirectPrerequisites().stream().map(TinyCourseDTO::fromCourse).toList();
            return new CourseDTO(course.getCode(), course.getName(), directPrereqs);
        }
    }
}
