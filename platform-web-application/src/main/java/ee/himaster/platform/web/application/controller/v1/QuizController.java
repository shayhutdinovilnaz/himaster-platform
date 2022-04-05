package ee.himaster.platform.web.application.controller.v1;

import ee.himaster.platform.api.controller.QuizApi;
import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.dto.QuizDto;
import java.math.BigDecimal;
import java.util.List;
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

    @Override
    public ResponseEntity<QuizDto> create(BigDecimal categoryId, String localeCode, BigDecimal userId, BigDecimal sessionId) {
        return null;
    }

    @Override
    public ResponseEntity<QuizDto> applyAnswer(String localeCode, Integer quizId, List<AnswerDto> body) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Integer quizId, String localeCode) {
        return null;
    }

    @Override
    public ResponseEntity<QuizDto> revertQuiz(Integer quizId, String localeCode) {
        return null;
    }
}
