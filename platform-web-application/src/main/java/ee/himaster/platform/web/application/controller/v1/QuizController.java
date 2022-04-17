package ee.himaster.platform.web.application.controller.v1;

import ee.himaster.platform.api.controller.QuizApi;
import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.dto.QuizDto;

import java.util.List;

import ee.himaster.platform.facades.facade.QuizFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/v1")
@RestController
@Slf4j
public class QuizController implements QuizApi {
    private final QuizFacade quizFacade;

    @Override
    public ResponseEntity<QuizDto> applyAnswer(String localeCode, Integer quizId, List<AnswerDto> answers) {
        return ResponseEntity.ok(quizFacade.applyAnswer(quizId, answers));
    }

    @Override
    public ResponseEntity<QuizDto> create(Integer categoryId, String localeCode, Integer userId, Integer sessionId) {
        return ResponseEntity.ok(quizFacade.create(userId, sessionId, categoryId));
    }

    @Override
    public ResponseEntity<Void> delete(Integer quizId, String localeCode) {
        quizFacade.delete(quizId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<QuizDto> revertQuiz(Integer quizId, String localeCode) {
        return ResponseEntity.ok(quizFacade.revert(quizId));
    }
}
