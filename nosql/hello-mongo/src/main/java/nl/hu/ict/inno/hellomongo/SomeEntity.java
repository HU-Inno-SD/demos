package nl.hu.ict.inno.hellomongo;

public class SomeEntity {
    private long id;
    private String name;

    public SomeEntity(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SomeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
