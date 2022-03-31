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
        final var question1 = new QuestionModel();
        question1.setId(1);

        final var question2 = new QuestionModel();
        question2.setId(2);

        final var nextQuestion = new QuestionModel();
        nextQuestion.setId(3);

        final var answerOption = new AnswerOptionModel();
        answerOption.setQuestion(question1);
        answerOption.setNextQuestion(nextQuestion);

        final var answer = new AnswerModel();
        answer.setOption(answerOption);
        final var answers = Lists.newArrayList(answer);

        final List<QuizItemModel> quizItems = new ArrayList<>();
        quizItems.add(createItem(question1, null, 0));
        quizItems.add(createItem(question2, null, 1));

        final var quiz = new QuizModel();
        quiz.setCurrentStep(1);
        quiz.setItems(quizItems);


        final var result = underTest.applyAnswers(answers, quiz);
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.getCurrentStep());
        Assert.assertEquals(3, result.getItems().size());

        Assert.assertEquals(question1, result.getItems().get(0).getQuestion());
        Assert.assertEquals(0, result.getItems().get(0).getOrder());

        Assert.assertEquals(question2, result.getItems().get(1).getQuestion());
        Assert.assertEquals(1, result.getItems().get(1).getOrder());
        Assert.assertEquals(answers, result.getItems().get(1).getAnswers());

        Assert.assertEquals(nextQuestion, result.getItems().get(2).getQuestion());
        Assert.assertEquals(2, result.getItems().get(2).getOrder());

        verify(quizRepository).save(quiz);
    }

    @Test
    public void applyAnswers_oneAnswerWithoutNextQuestionAndSummarizeQuestionNotExist_answersAreSubmit() {
        final var question1 = new QuestionModel();
        question1.setId(1);

        final var question2 = new QuestionModel();
        question2.setId(2);

        final var answerOption = new AnswerOptionModel();
        answerOption.setQuestion(question1);
        answerOption.setNextQuestion(null);

        final var answer = new AnswerModel();
        answer.setOption(answerOption);
        final var answers = Lists.newArrayList(answer);

        final List<QuizItemModel> quizItems = new ArrayList<>();
        quizItems.add(createItem(question1, null, 0));
        quizItems.add(createItem(question2, null, 1));

        final var category = new CategoryModel();

        final var quiz = new QuizModel();
        quiz.setCurrentStep(1);
        quiz.setCategory(category);
        quiz.setItems(quizItems);

        final var result = underTest.applyAnswers(answers, quiz);
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.getCurrentStep());
        Assert.assertEquals(2, result.getItems().size());

        Assert.assertEquals(question1, result.getItems().get(0).getQuestion());
        Assert.assertEquals(0, result.getItems().get(0).getOrder());

        Assert.assertEquals(question2, result.getItems().get(1).getQuestion());
        Assert.assertEquals(1, result.getItems().get(1).getOrder());
        Assert.assertEquals(answers, result.getItems().get(1).getAnswers());

        verify(questionRepository).findSummarizeQuestion(category);
        verify(quizRepository).save(quiz);
    }

    @Test
    public void applyAnswers_oneAnswerWithNextQuestionAndOneQuestionAheadAlso_answersAreSubmitAndNextItemIsAddedAndQuestionsAheadIsSaved() {
        final var question1 = new QuestionModel();
        question1.setId(1);

        final var question3 = new QuestionModel();
        question3.setId(2);

        final var nextQuestion = new QuestionModel();
        nextQuestion.setId(3);

        final var answerOption = new AnswerOptionModel();
        answerOption.setQuestion(question1);
        answerOption.setNextQuestion(nextQuestion);

        final var answer = new AnswerModel();
        answer.setOption(answerOption);
        final var answers = Lists.newArrayList(answer);

        final List<QuizItemModel> quizItems = new ArrayList<>();
        quizItems.add(createItem(question1, null, 0));
        quizItems.add(createItem(question3, null, 1));

        final var quiz = new QuizModel();
        quiz.setCurrentStep(0);
        quiz.setItems(quizItems);

        final var result = underTest.applyAnswers(answers, quiz);
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.getCurrentStep());
        Assert.assertEquals(3, result.getItems().size());

        Assert.assertEquals(question1, result.getItems().get(0).getQuestion());
        Assert.assertEquals(0, result.getItems().get(0).getOrder());
        Assert.assertEquals(answers, result.getItems().get(0).getAnswers());

        Assert.assertEquals(nextQuestion, result.getItems().get(1).getQuestion());
        Assert.assertEquals(1, result.getItems().get(1).getOrder());

        Assert.assertEquals(question3, result.getItems().get(2).getQuestion());
        Assert.assertEquals(2, result.getItems().get(2).getOrder());

        verify(quizRepository).save(quiz);
    }

    @Test
    public void applyAnswers_oneAnswerWithoutNextQuestionAndSummarizeQuestionIsAdded_answersAreSubmitAndNextItemIsAdded() {
        final var question1 = new QuestionModel();
        question1.setId(1);

        final var question2 = new QuestionModel();
        question2.setId(2);

        final var summarizeQuestion = new QuestionModel();
        summarizeQuestion.setId(3);

        final var answerOption = new AnswerOptionModel();
        answerOption.setQuestion(question2);
        answerOption.setNextQuestion(null);

        final var answer = new AnswerModel();
        answer.setOption(answerOption);
        final var answers = Lists.newArrayList(answer);

        final List<QuizItemModel> quizItems = new ArrayList<>();
        quizItems.add(createItem(question1, null, 0));
        quizItems.add(createItem(question2, null, 1));

        final var category = new CategoryModel();
        final var quiz = new QuizModel();
        quiz.setCurrentStep(1);
        quiz.setItems(quizItems);
        quiz.setCategory(category);

        when(questionRepository.findSummarizeQuestion(category)).thenReturn(summarizeQuestion);

        final var result = underTest.applyAnswers(answers, quiz);
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.getCurrentStep());
        Assert.assertEquals(3, result.getItems().size());

        Assert.assertEquals(question1, result.getItems().get(0).getQuestion());
        Assert.assertEquals(0, result.getItems().get(0).getOrder());

        Assert.assertEquals(question2, result.getItems().get(1).getQuestion());
        Assert.assertEquals(1, result.getItems().get(1).getOrder());
        Assert.assertEquals(answers, result.getItems().get(1).getAnswers());

        Assert.assertEquals(summarizeQuestion, result.getItems().get(2).getQuestion());
        Assert.assertEquals(2, result.getItems().get(2).getOrder());

        verify(questionRepository).findSummarizeQuestion(category);
        verify(quizRepository).save(quiz);
    }

    @Test
    public void applyAnswers_fewAnswersWithSameNextQuestion_answersAreSubmitAndNextItemIsAdded() {
        final var question1 = new QuestionModel();
        question1.setId(1);

        final var question2 = new QuestionModel();
        question2.setId(2);

        final var nextQuestion = new QuestionModel();
        nextQuestion.setId(3);

        final var answerOption1 = new AnswerOptionModel();
        answerOption1.setQuestion(question1);
        answerOption1.setNextQuestion(nextQuestion);

        final var answerOption2 = new AnswerOptionModel();
        answerOption2.setQuestion(question1);
        answerOption2.setNextQuestion(nextQuestion);

        final var answer1 = new AnswerModel();
        answer1.setOption(answerOption1);

        final var answer2 = new AnswerModel();
        answer2.setOption(answerOption2);

        final var answers = Lists.newArrayList(answer1, answer2);

        final List<QuizItemModel> quizItems = new ArrayList<>();
        quizItems.add(createItem(question1, null, 0));
        quizItems.add(createItem(question2, null, 1));

        final var quiz = new QuizModel();
        quiz.setCurrentStep(1);
        quiz.setItems(quizItems);


        final var result = underTest.applyAnswers(answers, quiz);
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.getCurrentStep());
        Assert.assertEquals(3, result.getItems().size());

        Assert.assertEquals(question1, result.getItems().get(0).getQuestion());
        Assert.assertEquals(0, result.getItems().get(0).getOrder());

        Assert.assertEquals(question2, result.getItems().get(1).getQuestion());
        Assert.assertEquals(1, result.getItems().get(1).getOrder());
        Assert.assertEquals(answers, result.getItems().get(1).getAnswers());

        Assert.assertEquals(nextQuestion, result.getItems().get(2).getQuestion());
        Assert.assertEquals(2, result.getItems().get(2).getOrder());

        verify(quizRepository).save(quiz);
    }

    @Test
    public void applyAnswers_fewAnswersWithDifferentNextQuestions_answersAreSubmitAndNextItemIsAdded() {
        final var question1 = new QuestionModel();
        question1.setId(1);

        final var question2 = new QuestionModel();
        question2.setId(2);

        final var nextQuestion1 = new QuestionModel();
        nextQuestion1.setId(3);

        final var nextQuestion2 = new QuestionModel();
        nextQuestion2.setId(4);

        final var answerOption1 = new AnswerOptionModel();
        answerOption1.setQuestion(question1);
        answerOption1.setNextQuestion(nextQuestion1);

        final var answerOption2 = new AnswerOptionModel();
        answerOption2.setQuestion(question1);
        answerOption2.setNextQuestion(nextQuestion2);

        final var answer1 = new AnswerModel();
        answer1.setOption(answerOption1);

        final var answer2 = new AnswerModel();
        answer2.setOption(answerOption2);

        final var answers = Lists.newArrayList(answer1, answer2);

        final List<QuizItemModel> quizItems = new ArrayList<>();
        quizItems.add(createItem(question1, null, 0));
        quizItems.add(createItem(question2, null, 1));

        final var quiz = new QuizModel();
        quiz.setCurrentStep(1);
        quiz.setItems(quizItems);


        final var result = underTest.applyAnswers(answers, quiz);
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.getCurrentStep());
        Assert.assertEquals(4, result.getItems().size());

        Assert.assertEquals(question1, result.getItems().get(0).getQuestion());
        Assert.assertEquals(0, result.getItems().get(0).getOrder());

        Assert.assertEquals(question2, result.getItems().get(1).getQuestion());
        Assert.assertEquals(1, result.getItems().get(1).getOrder());
        Assert.assertEquals(answers, result.getItems().get(1).getAnswers());

        Assert.assertEquals(nextQuestion1, result.getItems().get(2).getQuestion());
        Assert.assertEquals(2, result.getItems().get(2).getOrder());

        Assert.assertEquals(nextQuestion2, result.getItems().get(3).getQuestion());
        Assert.assertEquals(3, result.getItems().get(3).getOrder());

        verify(quizRepository).save(quiz);
    }

    @Test
    public void applyAnswers_fewAnswersOnlyOneNextQuestion_answersAreSubmitAndNextItemIsAdded() {
        final var question1 = new QuestionModel();
        question1.setId(1);

        final var question2 = new QuestionModel();
        question2.setId(2);

        final var nextQuestion = new QuestionModel();
        nextQuestion.setId(3);

        final var answerOption1 = new AnswerOptionModel();
        answerOption1.setQuestion(question1);
        answerOption1.setNextQuestion(nextQuestion);

        final var answerOption2 = new AnswerOptionModel();
        answerOption2.setQuestion(question1);
        answerOption2.setNextQuestion(null);

        final var answer1 = new AnswerModel();
        answer1.setOption(answerOption1);

        final var answer2 = new AnswerModel();
        answer2.setOption(answerOption2);

        final var answers = Lists.newArrayList(answer1, answer2);

        final List<QuizItemModel> quizItems = new ArrayList<>();
        quizItems.add(createItem(question1, null, 0));
        quizItems.add(createItem(question2, null, 1));

        final var quiz = new QuizModel();
        quiz.setCurrentStep(1);
        quiz.setItems(quizItems);


        final var result = underTest.applyAnswers(answers, quiz);
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.getCurrentStep());
        Assert.assertEquals(3, result.getItems().size());

        Assert.assertEquals(question1, result.getItems().get(0).getQuestion());
        Assert.assertEquals(0, result.getItems().get(0).getOrder());

        Assert.assertEquals(question2, result.getItems().get(1).getQuestion());
        Assert.assertEquals(1, result.getItems().get(1).getOrder());
        Assert.assertEquals(answers, result.getItems().get(1).getAnswers());

        Assert.assertEquals(nextQuestion, result.getItems().get(2).getQuestion());
        Assert.assertEquals(2, result.getItems().get(2).getOrder());

        verify(quizRepository).save(quiz);
    }

    @Test
    public void applyAnswers_fewAnswersWithoutNextQuestions_answersAreSubmitAndSummarizeItemIsAdded() {
        final var question1 = new QuestionModel();
        question1.setId(1);

        final var question2 = new QuestionModel();
        question2.setId(2);

        final var summarizeQuestion = new QuestionModel();
        summarizeQuestion.setId(3);

        final var answerOption1 = new AnswerOptionModel();
        answerOption1.setQuestion(question1);
        answerOption1.setNextQuestion(null);

        final var answerOption2 = new AnswerOptionModel();
        answerOption2.setQuestion(question1);
        answerOption2.setNextQuestion(null);

        final var answer1 = new AnswerModel();
        answer1.setOption(answerOption1);

        final var answer2 = new AnswerModel();
        answer2.setOption(answerOption2);

        final var answers = Lists.newArrayList(answer1, answer2);

        final List<QuizItemModel> quizItems = new ArrayList<>();
        quizItems.add(createItem(question1, null, 0));
        quizItems.add(createItem(question2, null, 1));

        final var category = new CategoryModel();
        final var quiz = new QuizModel();
        quiz.setCurrentStep(1);
        quiz.setCategory(category);
        quiz.setItems(quizItems);

        when(questionRepository.findSummarizeQuestion(category)).thenReturn(summarizeQuestion);

        final var result = underTest.applyAnswers(answers, quiz);
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.getCurrentStep());
        Assert.assertEquals(3, result.getItems().size());

        Assert.assertEquals(question1, result.getItems().get(0).getQuestion());
        Assert.assertEquals(0, result.getItems().get(0).getOrder());

        Assert.assertEquals(question2, result.getItems().get(1).getQuestion());
        Assert.assertEquals(1, result.getItems().get(1).getOrder());
        Assert.assertEquals(answers, result.getItems().get(1).getAnswers());

        Assert.assertEquals(summarizeQuestion, result.getItems().get(2).getQuestion());
        Assert.assertEquals(2, result.getItems().get(2).getOrder());

        verify(questionRepository).findSummarizeQuestion(category);
        verify(quizRepository).save(quiz);
    }

    @Test
    public void applyAnswers_fewAnswersWithoutNextQuestionsAndSummarizeQuestionIsAddedAlready_answersAreSubmitAndNextItemIsNotAdded() {
        final var question1 = new QuestionModel();
        question1.setId(1);

        final var summarizeQuestion = new QuestionModel();
        summarizeQuestion.setId(3);

        final var answerOption1 = new AnswerOptionModel();
        answerOption1.setQuestion(question1);
        answerOption1.setNextQuestion(null);

        final var answerOption2 = new AnswerOptionModel();
        answerOption2.setQuestion(question1);
        answerOption2.setNextQuestion(null);

        final var answer1 = new AnswerModel();
        answer1.setOption(answerOption1);

        final var answer2 = new AnswerModel();
        answer2.setOption(answerOption2);

        final var answers = Lists.newArrayList(answer1, answer2);

        final List<QuizItemModel> quizItems = new ArrayList<>();
        quizItems.add(createItem(question1, null, 0));
        quizItems.add(createItem(summarizeQuestion, null, 1));

        final var category = new CategoryModel();
        final var quiz = new QuizModel();
        quiz.setCurrentStep(1);
        quiz.setCategory(category);
        quiz.setItems(quizItems);

        when(questionRepository.findSummarizeQuestion(category)).thenReturn(summarizeQuestion);

        final var result = underTest.applyAnswers(answers, quiz);
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.getCurrentStep());
        Assert.assertEquals(2, result.getItems().size());

        Assert.assertEquals(question1, result.getItems().get(0).getQuestion());
        Assert.assertEquals(0, result.getItems().get(0).getOrder());

        Assert.assertEquals(summarizeQuestion, result.getItems().get(1).getQuestion());
        Assert.assertEquals(1, result.getItems().get(1).getOrder());
        Assert.assertEquals(answers, result.getItems().get(1).getAnswers());

        verify(questionRepository).findSummarizeQuestion(category);
        verify(quizRepository).save(quiz);

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