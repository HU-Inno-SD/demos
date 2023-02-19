package nl.hu.sd.inno.dddexamples.survey.aggregate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="AggregateAnswer")
public class SurveyAnswer {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private SurveyQuestion question;

    private String label;

    protected SurveyAnswer(){}

    public SurveyAnswer(String label){
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public SurveyQuestion getQuestion() {
        return question;
    }

    void setQuestion(SurveyQuestion question) {
        this.question = question;
    }

    public String getLabel() {
        return label;
    }
}
