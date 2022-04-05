package ee.himaster.platform.facades.facade;

import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.dto.QuizDto;
import java.util.List;

public interface QuizFacade {

    QuizDto create(final Integer userId, final Integer sessionId, final Integer categoryId);

    QuizDto applyAnswer(final Integer quizId, final List<AnswerDto> answers);

    QuizDto revert(final Integer quizId);

    void delete(final Integer quizId);

}
