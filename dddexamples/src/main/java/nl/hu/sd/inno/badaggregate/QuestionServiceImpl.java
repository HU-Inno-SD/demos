package nl.hu.sd.inno.badaggregate;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {


    private QuestionRepository questions;

    public QuestionServiceImpl(QuestionRepository questions){
        this.questions = questions;
    }

    @Override
    public List<Question> getQuestions(int pagenr, String language){
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
