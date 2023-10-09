package nl.hu.sd.inno.badaggregate.registrations;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {


    private QuestionRepository questions;
    private RegistrationRepository registrations;

    public QuestionServiceImpl(QuestionRepository questions, RegistrationRepository registrations){
        this.questions = questions;
        this.registrations = registrations;
    }

    @Override
    public List<Question> getQuestions(String registrationId, int pagenr, String language){
        Registration registration = this.registrations.findById(registrationId).orElseThrow();

        List<Question> questions = this.questions.findAll();

        for(Question q: questions){
            for(QuestionLocalization ql: q.getLocalizedVersions()){
                if(ql.getLanguage().equals(language)){
                    q.setLabel(ql.getLabel());
                }
            }
        }

        return questions;
    }
}
