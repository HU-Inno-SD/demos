package nl.hu.sd.inno.dddexamples.survey.aggregate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity(name="AggregateQuestion")
public class SurveyQuestion {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL) //Maar nog makkelijker zou zijn om niet bidirectioneel te zijn
    private List<SurveyAnswer> answers = new ArrayList<>();

    private String label;

    public Long getId() {
        return id;
    }

    protected SurveyQuestion(){}

    public SurveyQuestion(String label){
        this.label = label;
    }

    public List<SurveyAnswer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public String getLabel() {
        return label;
    }

    public void removeAnswer(SurveyAnswer a) {
        if(this.answers.remove(a)){
            a.setQuestion(null);
        }
    }

    public void addAnswer(SurveyAnswer a) {
        if(a.getQuestion() != this && a.getQuestion() != null){
            a.getQuestion().removeAnswer(a);
        }

        this.answers.add(a);
        a.setQuestion(this);
    }
}
