package nl.hu.sd.inno.badaggregate;

import nl.hu.sd.inno.badaggregate.exhibitions.TradeFair;
import nl.hu.sd.inno.badaggregate.registrations.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
        this.registrations.save(new Registration("test", new TradeFair("Pokemon Van Gogh", LocalDateTime.now().plusDays(6))));

        Question q1 = new Question("Hoe gaat het?");
        q1.getLocalizedVersions().add(new QuestionLocalization("How are you?", "eng"));
        q1.getLocalizedVersions().add(new QuestionLocalization("Comment allez-vous?", "fra"));

        this.questions.save(q1);


    }
}
