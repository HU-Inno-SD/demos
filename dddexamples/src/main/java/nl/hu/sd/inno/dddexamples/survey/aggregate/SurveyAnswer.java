package nl.hu.sd.inno.dddexamples.survey.aggregate;

import nl.hu.sd.inno.dddexamples.survey.noddd.SurveyQuestion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

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
