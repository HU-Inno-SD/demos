package nl.hu.sd.inno.badaggregate.registrations;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestions(String registrationId, int pagenr, String language);
}
