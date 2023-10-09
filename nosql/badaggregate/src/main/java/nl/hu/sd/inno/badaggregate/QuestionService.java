package nl.hu.sd.inno.badaggregate;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestions(int pagenr, String language);
}
