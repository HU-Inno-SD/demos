package nl.hu.ict.inno.hellogateways.courses;

import java.util.Objects;

public class ECTS {
    private static final int DEFAULT_COURSE_CREDITS = 5;
    private static final int FULL_YEAR_CREDITS = 60;
    private static final int HALF_YEAR_CREDITS = FULL_YEAR_CREDITS / 2;


    public int value() {
        return value;
    }

    private int value;

    private ECTS(int value){
        if(value < 0){
            throw new IllegalArgumentException("ECTS must be >0, received " + value);
        }
        if(value > 60){
            throw new IllegalArgumentException("ECTS must be >0, received " + value);
        }
        this.value = value;
    }

    public static ECTS course(){
        return new ECTS(DEFAULT_COURSE_CREDITS);
    }

    public static ECTS semester(){
        return new ECTS(HALF_YEAR_CREDITS);
    }

    public static ECTS year(){
        return new ECTS(FULL_YEAR_CREDITS);
    }

    public static ECTS from_database(int ects){
        return new ECTS(ects);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ECTS ects = (ECTS) o;
        return value == ects.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
