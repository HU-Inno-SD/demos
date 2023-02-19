package nl.hu.sd.inno.dddexamples.survey.aggregate;

import nl.hu.sd.inno.dddexamples.survey.noddd.SurveyQuestion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="AggregateAnswer")
public class SurveyAnswer {

    @Id
    @GeneratedValue
    private Long id;

    private String label;

    protected SurveyAnswer(){}

    protected SurveyAnswer(String label){
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}
