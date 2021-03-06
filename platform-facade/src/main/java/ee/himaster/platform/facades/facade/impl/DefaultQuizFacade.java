package ee.himaster.platform.facades.facade.impl;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.dto.QuizDto;
import ee.himaster.platform.facades.facade.QuizFacade;
import ee.himaster.platform.services.model.quiz.QuizItemModel;
import ee.himaster.platform.services.model.quiz.QuizModel;
import ee.himaster.platform.services.model.quiz.answer.AnswerModel;
import ee.himaster.platform.services.service.CategoryService;
import ee.himaster.platform.services.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultQuizFacade implements QuizFacade {
    private final QuizService quizService;
    private final CategoryService categoryService;
    private final Converter<QuizDto, QuizModel> quizConverter;
    private final Converter<AnswerDto, AnswerModel> answerConverter;

    @Override
    public QuizDto create(final Integer userId, final Integer sessionId, final Integer categoryId) {
        final var category = categoryService.getById(categoryId);
        final var newQuiz = quizService.create(userId, sessionId, category);

        newQuiz.setItems(getFirstQuizItem(newQuiz));

        quizService.save(newQuiz);

        return quizConverter.convert(newQuiz);
    }

    private List<QuizItemModel> getFirstQuizItem(QuizModel newQuiz) {
        final var quizItem = new QuizItemModel();
        quizItem.setQuestion(quizService.getNextQuestion(newQuiz));
        quizItem.setQuiz(newQuiz);

        List<QuizItemModel> itemModels = new ArrayList<>();
        itemModels.add(quizItem);
        return itemModels;
    }

    @Override
    public QuizDto applyAnswer(final Integer quizId, final List<AnswerDto> answersDto) {
        final var quiz = quizService.getById(quizId);
        final var answers = answersDto.stream()
                .map(answerConverter::reverseConvert)
                .collect(Collectors.toList());
        final var recalculatedQuiz = quizService.applyAnswers(answers, quiz);
        return quizConverter.convert(recalculatedQuiz);
    }

    @Override
    public QuizDto revert(final Integer quizId) {
        final var quiz = quizService.getById(quizId);
        final var revertedQuiz = quizService.revertToPreviousQuestion(quiz);
        return quizConverter.convert(revertedQuiz);
    }

    @Override
    public void delete(final Integer quizId) {
        final var quiz = quizService.getById(quizId);
        quizService.delete(quiz);
    }
}
