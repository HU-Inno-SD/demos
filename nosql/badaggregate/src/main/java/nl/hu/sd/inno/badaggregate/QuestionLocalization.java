package nl.hu.sd.inno.badaggregate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class QuestionLocalization {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    protected QuestionLocalization(){}

    public QuestionLocalization(String label, String lang) {
        this.label = label;
        this.language = lang;
    }

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    private String language;

    public String getLanguage() {
        return language;
    }
}
