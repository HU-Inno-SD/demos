package nl.hu.ict.clientsdatarest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitialDataRunner implements CommandLineRunner {

    @Autowired
    private StudentRepository students;

    @Autowired
    private CourseRepository courses;

    @Autowired
    private StaffRepository staff;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Staff slber = new Staff("Irene");
        Course prog = new Course("Programming");
        Course bep1 = new Course("BEP1");


        staff.save(slber);
        courses.save(prog);
        courses.save(bep1);

        Student dummy = new Student("Bob");

        dummy.setSlb(slber);
        dummy.getCourses().add(prog);
        dummy.getCourses().add(bep1);

        students.save(dummy);

    }
}
