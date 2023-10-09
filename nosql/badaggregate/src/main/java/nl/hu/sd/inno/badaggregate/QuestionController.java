package nl.hu.sd.inno.badaggregate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionController {


    private QuestionService questionService;
    private RegistrationService registrationService;


    public QuestionController(QuestionService questionService, RegistrationService registrationService) {
        this.questionService = questionService;
        this.registrationService = registrationService;
    }

    @GetMapping("/registration/{id}/questions/")
    public List<Question> getQuestions(@PathVariable("id") String registrationId,
                                       @RequestParam(value = "page", required = true) int page,
                                       @RequestParam(value = "lang", required = true) String lang) {

        List<Question> questions = this.questionService.getQuestions(page, lang);
//        Deze regel ontcommenten, en dan zie je hoe JPA/Hibernate soms een best lekke abstractie is.
//        Let op: m'n punt is niet "Stoute JPA!", maar dat je vaak best een diep conceptueel begrip van je libraries
//        nodig hebt om redelijkerwijs een probleem te debuggen.
//        this.registrationService.markPage(registrationId, page);

        return questions;
    }
}
