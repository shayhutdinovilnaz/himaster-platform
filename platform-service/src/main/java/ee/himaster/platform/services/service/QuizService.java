package ee.himaster.platform.services.service;

import ee.himaster.core.service.service.ModelService;
import ee.himaster.platform.services.model.CategoryModel;
import ee.himaster.platform.services.model.quiz.QuestionModel;
import ee.himaster.platform.services.model.quiz.QuizModel;
import ee.himaster.platform.services.model.quiz.answer.AnswerModel;
import java.util.List;

public interface QuizService extends ModelService<QuizModel> {

    QuizModel create(Integer userId, Integer sessionId, CategoryModel category);

    QuizModel getByUser(Integer userId);

    QuestionModel getNextQuestion(QuizModel quiz);

    QuizModel applyAnswers(List<AnswerModel> answers, QuizModel quiz);

    QuizModel revertToPreviousQuestion(QuizModel quiz);
}
