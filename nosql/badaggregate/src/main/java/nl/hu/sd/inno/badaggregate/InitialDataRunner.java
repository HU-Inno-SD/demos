package nl.hu.sd.inno.badaggregate;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class InitialDataRunner implements ApplicationRunner {

    private RegistrationRepository registrations;
    private QuestionRepository questions;

    public InitialDataRunner(RegistrationRepository registrations, QuestionRepository questions) {
        this.registrations = registrations;
        this.questions = questions;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.registrations.save(new Registration("test"));

        Question q1 = new Question("Hoe gaat het?");
        q1.getLocalizedVersions().add(new QuestionLocalization("How are you?", "eng"));
        q1.getLocalizedVersions().add(new QuestionLocalization("Comment allez-vous?", "fra"));

        this.questions.save(q1);


    }
}
