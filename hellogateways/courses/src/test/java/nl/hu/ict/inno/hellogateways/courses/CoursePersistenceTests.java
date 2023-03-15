package nl.hu.ict.inno.hellogateways.courses;

import jakarta.persistence.EntityManager;
import nl.hu.ict.inno.hellogateways.courses.CourseLevel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CoursePersistenceTests {

    @Autowired
    private EntityManager entities;

    @Test
    public void canPersistCourse(){
        Course c = new Course("ABC", "Test", CourseLevel.Freshman);

        entities.persist(c);
        entities.flush();
        entities.clear();

        Course found = entities.find(Course.class, c.getId().value());

        assertEquals(found, c);
    }

    @Test
    public void canCascasePrerequisiteCourses(){
        Course prereq = new Course("PREQ", "Test Prereq", CourseLevel.Freshman);
        Course c = new Course("ABC", "Test", CourseLevel.Freshman, List.of(prereq));

        entities.persist(c);
        entities.flush();
        entities.clear();

        Course found = entities.find(Course.class, prereq.getId().value());

        assertEquals(found, prereq);
    }


}
