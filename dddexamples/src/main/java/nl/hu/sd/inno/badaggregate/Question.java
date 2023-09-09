package nl.hu.sd.inno.badaggregate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    protected Question(){}

    public Question(String label){
        this.label = label;
    }

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<QuestionLocalization> localizedVersions = new ArrayList<>();

    public List<QuestionLocalization> getLocalizedVersions() {
        return localizedVersions;
    }
}
