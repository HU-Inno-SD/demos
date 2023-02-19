package nl.hu.sd.inno.dddexamples.survey.noddd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class QuestionPersistenceTest {

    @Autowired
    private EntityManager entities;


    @Test
    public void canPersistQuestions() {
        SurveyQuestion question = new SurveyQuestion("Alles ok?");
        SurveyAnswer ja = new SurveyAnswer("Ja");
        SurveyAnswer nee = new SurveyAnswer("Nee");

        question.getAnswers().add(ja); //Het risico is dus dat je deze regels overal handmatig in sync moet houden
        question.getAnswers().add(nee);
        ja.setQuestion(question);
        nee.setQuestion(question);

        entities.persist(question);

        entities.flush();
        entities.clear();

        SurveyQuestion found = entities.find(SurveyQuestion.class, question.getId());

        assertEquals("Alles ok?", found.getLabel());
        assertEquals(2, found.getAnswers().size());
        assertEquals("Ja", found.getAnswers().get(0).getLabel());
        assertEquals("Nee", found.getAnswers().get(1).getLabel());
    }
}
