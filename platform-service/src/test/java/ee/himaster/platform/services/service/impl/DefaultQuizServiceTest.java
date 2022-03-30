package ee.himaster.platform.services.service.impl;

import ee.himaster.core.service.exception.ModelNotFoundException;
import ee.himaster.core.service.service.impl.AbstractModelService;
import ee.himaster.platform.services.model.CategoryModel;
import ee.himaster.platform.services.model.quiz.QuestionModel;
import ee.himaster.platform.services.model.quiz.QuizItemModel;
import ee.himaster.platform.services.model.quiz.QuizModel;
import ee.himaster.platform.services.model.quiz.answer.AnswerModel;
import ee.himaster.platform.services.model.quiz.answer.option.AnswerOptionModel;
import ee.himaster.platform.services.repository.QuestionRepository;
import ee.himaster.platform.services.repository.QuizItemRepository;
import ee.himaster.platform.services.repository.QuizRepository;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultQuizServiceTest extends AbstractModelServiceTest<QuizModel> {

    @InjectMocks
    private DefaultQuizService underTest;

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private QuizItemRepository quizItemRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Test
    public void create_argumentsValid_success() {
        var userId = 1;
        var category = new CategoryModel();

        final var quizModel = underTest.create(userId, category);

        verify(quizRepository).save(quizModel);
        Assert.assertEquals(userId, quizModel.getUserId(), 0);
        Assert.assertEquals(category, quizModel.getCategory());
    }

    @Test
    public void getByUser_userExist_success() {
        var userId = 1;
        var quiz = new QuizModel();

        when(quizRepository.getByUserId(userId)).thenReturn(quiz);

        final var result = underTest.getByUser(userId);
        Assert.assertEquals(quiz, result);
    }

    @Test(expected = ModelNotFoundException.class)
    public void getByUser_userNotExist_exception() {
        var userId = 1;

        when(quizRepository.getByUserId(userId)).thenReturn(null);

        underTest.getByUser(userId);
    }

    @Test
    public void getNextQuestion_initializeQuestionNotExist_null() {
        var category = new CategoryModel();
        var quiz = new QuizModel();
        quiz.setCategory(category);
        quiz.setCurrentStep(0);

        when((questionRepository.findInitializeQuestion(category))).thenReturn(null);

        final var nextQuestion = underTest.getNextQuestion(quiz);
        Assert.assertNull(nextQuestion);

        verify(questionRepository).findInitializeQuestion(category);
        verifyNoMoreInteractions(questionRepository);
    }

    @Test
    public void getNextQuestion_initializeQuestionExist_success() {
        var category = new CategoryModel();
        var initializeQuestion = new QuestionModel();
        var quiz = new QuizModel();
        quiz.setCategory(category);
        quiz.setCurrentStep(0);

        when((questionRepository.findInitializeQuestion(category))).thenReturn(initializeQuestion);

        final var nextQuestion = underTest.getNextQuestion(quiz);
        Assert.assertEquals(initializeQuestion, nextQuestion);

        verify(questionRepository).findInitializeQuestion(category);
        verifyNoMoreInteractions(questionRepository);
    }

    @Test
    public void getNextQuestion_nextQuestionExist_success() {
        var category = new CategoryModel();
        var firstQuestion = new QuestionModel();
        var secondQuestion = new QuestionModel();
        var thirdQuestion = new QuestionModel();

        var quizItems =
                List.of(createItem(firstQuestion, null, 0), createItem(secondQuestion, null, 1), createItem(thirdQuestion, null, 2));

        var quiz = new QuizModel();
        quiz.setCategory(category);
        quiz.setItems(quizItems);
        quiz.setCurrentStep(1);

        final var nextQuestion = underTest.getNextQuestion(quiz);
        Assert.assertEquals(secondQuestion, nextQuestion);

        verifyNoInteractions(questionRepository);
    }

    @Test
    public void getNextQuestion_nextQuestionNotExist_null() {
        var category = new CategoryModel();
        var firstQuestion = new QuestionModel();
        var secondQuestion = new QuestionModel();
        var thirdQuestion = new QuestionModel();

        var quizItems =
                List.of(createItem(firstQuestion, null, 0), createItem(secondQuestion, null, 1), createItem(thirdQuestion, null, 2));

        var quiz = new QuizModel();
        quiz.setCategory(category);
        quiz.setItems(quizItems);
        quiz.setCurrentStep(4);

        final var nextQuestion = underTest.getNextQuestion(quiz);
        Assert.assertNull(nextQuestion);
    }

    @Test
    public void applyAnswers_oneAnswerWithNextQuestion_answersAreSubmitAndNextItemIsAdded() {
        final var question = new QuestionModel();
        question.setId(1);

        final var nextQuestion = new QuestionModel();
        nextQuestion.setId(2);

        final var answerOption = new AnswerOptionModel();
        answerOption.setQuestion(question);
        answerOption.setNextQuestion(nextQuestion);

        final var answer = new AnswerModel();
        answer.setOption(answerOption);
        final var answers = Lists.newArrayList(answer);

        final List<QuizItemModel> quizItems = new ArrayList<>();
        quizItems.add(createItem(question, null, 0));

        final var quiz = new QuizModel();
        quiz.setCurrentStep(0);
        quiz.setItems(quizItems);


        final var result = underTest.applyAnswers(answers, quiz);
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.getCurrentStep());
        Assert.assertEquals(2, result.getItems().size());

        Assert.assertEquals(question, result.getItems().get(0).getQuestion());
        Assert.assertEquals(0, result.getItems().get(0).getOrder());
        Assert.assertEquals(answers, result.getItems().get(0).getAnswers());

        Assert.assertEquals(nextQuestion, result.getItems().get(1).getQuestion());
        Assert.assertEquals(1, result.getItems().get(1).getOrder());

        verify(quizRepository).save(quiz);
    }

    public void applyAnswers_oneAnswerWithoutNextQuestionAndSummarizeQuestionNotExist_answersAreSubmit() {

    }

    public void applyAnswers_oneAnswerWithNextQuestionAndOneQuestionAheadAlso_answersAreSubmitAndNextItemIsAddedAndQuestionsAheadIsSaved() {

    }

    public void applyAnswers_oneAnswerWithoutNextQuestionAndSummarizeQuestionIsAdded_answersAreSubmitAndNextItemIsAdded() {

    }

    public void applyAnswers_fewAnswersWithNextQuestion_answersAreSubmitAndNextItemIsAdded() {

    }

    public void applyAnswers_fewAnswersWithDifferentNextQuestions_answersAreSubmitAndNextItemIsAdded() {

    }

    public void applyAnswers_fewAnswersOnlyOneNextQuestion_answersAreSubmitAndNextItemIsAdded() {

    }

    public void applyAnswers_fewAnswersWithoutNextQuestions_answersAreSubmitAndSummarizeItemIsAdded() {

    }

    public void applyAnswers_fewAnswersWithoutNextQuestionsAndSummarizeQuestionIsAddedAlready_answersAreSubmitAndNextItemIsNotAdded() {

    }

    private QuizItemModel createItem(QuestionModel question, List<AnswerModel> answers, int order) {
        var quizItemModel = new QuizItemModel();
        quizItemModel.setQuestion(question);
        quizItemModel.setOrder(order);
        quizItemModel.setAnswers(answers);

        return quizItemModel;
    }

    @Override
    protected Class<QuizModel> getGenericClassOfService() {
        return QuizModel.class;
    }

    @Override
    protected AbstractModelService<QuizModel> getGenericModelService() {
        return underTest;
    }

    @Override
    protected JpaRepository<QuizModel, Integer> getModelRepository() {
        return quizRepository;
    }
}