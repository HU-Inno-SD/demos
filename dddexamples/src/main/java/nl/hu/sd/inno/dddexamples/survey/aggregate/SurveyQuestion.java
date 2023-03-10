package nl.hu.sd.inno.dddexamples.survey.aggregate;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity(name = "AggregateQuestion")
public class SurveyQuestion {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL) //Maar nog makkelijker zou zijn om niet bidirectioneel te zijn
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

    public String getLabel() {
        return label;
    }

    public SurveyAnswer createAnswer(String label) {
        SurveyAnswer a = new SurveyAnswer(label);
        this.answers.add(a);
        return a;
    }

    public void moveAnswerTo(SurveyQuestion target, SurveyAnswer answer) {
        this.answers.remove(answer);
        target.answers.add(answer);
    }
}
