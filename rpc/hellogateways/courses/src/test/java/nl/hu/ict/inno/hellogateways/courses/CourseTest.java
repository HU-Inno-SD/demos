package nl.hu.ict.inno.hellogateways.courses;

import nl.hu.ict.inno.hellogateways.courses.CourseLevel;
import nl.hu.ict.inno.hellogateways.courses.ECTS;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.Collection;
import java.util.List;

class CourseTest {
    private static <T> void assertContainsSame(Collection<T> colA, Collection<T> colB){
        boolean allIn = true;

        for(T item: colA){
            allIn = allIn && colB.contains(item);
        }
        for(T item: colB){
            allIn = allIn && colA.contains(item);
        }

        if(!allIn){
            throw new AssertionFailedError("Collections contain different items", colA, colB);
        }
    }


    @Test
    public void getAllPrerequisites() {
        Course programming = new Course("PROG", "Programming", CourseLevel.Freshman);
        Course BEP1 = new Course("BEP1", "Backend Programming 1", CourseLevel.Freshman, List.of(programming));
        Course DataPersist = new Course("D&P", "Data & Persistency", CourseLevel.Sophomore, List.of(programming));
        Course BEP2 = new Course("BEP2", "Backend Programming 2", CourseLevel.Sophomore, List.of(BEP1, DataPersist));
        Course Inno = new Course("INNO-SD", "INNO SD Backend Kennisroute", CourseLevel.Bachelors, ECTS.semester(), List.of(BEP2));

        List<Course> prereqs = List.of(programming, BEP1, DataPersist, BEP2);
        assertContainsSame(prereqs, Inno.getAllPrerequisites());

    }
}
