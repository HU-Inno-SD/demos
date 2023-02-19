package nl.hu.sd.inno.dddexamples.survey.noddd;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity(name = "noDDDQuestion")
public class SurveyQuestion {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<SurveyAnswer> answers = new ArrayList<>();

    private String label;

    public Long getId() {
        return id;
    }

    protected SurveyQuestion() {
    }

    public SurveyQuestion(String label) {
        this.label = label;
    }

    public List<SurveyAnswer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public void removeAnswer(SurveyAnswer a) {
        this.answers.remove(a);
        if (a.getQuestion() != null) {
            a.setQuestion(null);
        }
    }

    public void addAnswer(SurveyAnswer a) {
        if (a.getQuestion() == this) {
            this.answers.add(a);
        } else {
            a.setQuestion(this);
        }
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SurveyQuestion that = (SurveyQuestion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
