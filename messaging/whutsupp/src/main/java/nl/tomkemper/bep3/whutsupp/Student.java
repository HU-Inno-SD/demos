package nl.tomkemper.bep3.whutsupp;

import org.springframework.data.annotation.Id;

import static nl.tomkemper.bep3.whutsupp.Whutsupp.*;

public class Student {

    @Id
    private long studentNr;
    private String givenName;
    private String prefix;
    private String surname;
    private String email;

    protected Student() {
    }

    public Student(long studentNr, String givenName, String prefix, String surname, String email) {
        this.studentNr = studentNr;
        this.givenName = givenName;
        this.prefix = prefix;
        this.surname = surname;
        this.email = email;
    }

    public Student(long studentNr, String givenName, String surname, String email) {
        this(studentNr, givenName, "", surname, email);
    }


    public long getStudentNr() {
        return studentNr;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public static String getRoutingKey(Student student) {
        return getRoutingKey(student.getStudentNr());
    }

    public static String getRoutingKey(long studentnr) {
        return String.format("%s.%s", PM_EXCHANGE, studentnr);
    }

}
