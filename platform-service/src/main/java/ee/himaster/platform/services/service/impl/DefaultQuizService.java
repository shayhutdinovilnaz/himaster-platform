package ee.himaster.platform.services.service.impl;

import ee.himaster.core.service.exception.ModelNotFoundException;
import ee.himaster.core.service.model.ItemModel;
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
import ee.himaster.platform.services.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultQuizService extends AbstractModelService<QuizModel> implements QuizService {
    private final QuizRepository quizRepository;
    private final QuizItemRepository quizItemRepository;
    private final QuestionRepository questionRepository;

    @Override
    public QuizModel create(final Integer userId, final CategoryModel category) {

        Objects.requireNonNull(userId);
        Objects.requireNonNull(category);

        var quizModel = new QuizModel();
        quizModel.setUserId(userId);
        quizModel.setCategory(category);
        save(quizModel);
        return quizModel;
    }

    @Override
    public QuizModel getByUser(final Integer userId) {

        Objects.requireNonNull(userId);

        return Optional.ofNullable(quizRepository.getByUserId(userId))
                .orElseThrow(() -> new ModelNotFoundException("The quiz is not found for user. User id: " + userId));
    }

    @Override
    public QuestionModel getNextQuestion(final QuizModel quiz) {
        Objects.requireNonNull(quiz);

        return retrieveNextQuestion(quiz);
    }

    private QuestionModel retrieveNextQuestion(final QuizModel quiz) {
        QuestionModel question = null;
        if (isFirstQuestion(quiz)) {
            question = questionRepository.findInitializeQuestion(quiz.getCategory());
        } else {
            for (var item : quiz.getItems()) {
                final int nextItemOrder = quiz.getCurrentStep();
                if (item.getOrder() == nextItemOrder) {
                    question = item.getQuestion();
                    break;
                }
            }
        }

        return question;
    }

    private boolean isFirstQuestion(final QuizModel quiz) {
        return quiz.getCurrentStep() == 0;
    }

    @Override
    public QuizModel applyAnswers(final List<AnswerModel> answers, final QuizModel quiz) {
        Objects.requireNonNull(answers);
        Objects.requireNonNull(quiz);

        addAnswers(answers, quiz);

        recalculate(answers, quiz);

        save(quiz);

        log.info("A new answer was applied. The quiz was recalculated. Quiz id: {}.", quiz.getId());
        return quiz;
    }

    private void addAnswers(final List<AnswerModel> answers, final QuizModel quiz) {
        for (var item : quiz.getItems()) {
            if (item.getOrder() == quiz.getCurrentStep()) {
                item.setAnswers(answers);
                break;
            }
        }
    }

    private void recalculate(final List<AnswerModel> answers, final QuizModel quiz) {
        final var items = quiz.getItems();
        final var newQuizItems = createNewQuizItems(answers, quiz);

        for (QuizItemModel existingItem : items) {
            if (existingItem.getOrder() > quiz.getCurrentStep()) {
                existingItem.setOrder(existingItem.getOrder() + newQuizItems.size());
            }
        }

        items.addAll(newQuizItems);
        items.sort(Comparator.comparingInt(QuizItemModel::getOrder));

        quiz.setCurrentStep(quiz.getCurrentStep() + 1);
    }

    private List<QuizItemModel> createNewQuizItems(List<AnswerModel> answers, QuizModel quiz) {
        int nextOrder = quiz.getCurrentStep();
        final List<QuizItemModel> newQuizItems = new ArrayList<>();
        final List<Integer> newQuestions = new ArrayList<>();

        for (QuestionModel nextQuestion : getNextQuestions(answers, quiz)) {
            if (!newQuestions.contains(nextQuestion.getId())) {
                nextOrder++;
                final var nextQuizItem = QuizItemModel
                        .builder()
                        .order(nextOrder)
                        .question(nextQuestion)
                        .quiz(quiz)
                        .build();

                newQuizItems.add(nextQuizItem);
                newQuestions.add(nextQuestion.getId());
            }
        }

        return newQuizItems;
    }

    private List<QuestionModel> getNextQuestions(final List<AnswerModel> answers, final QuizModel quiz) {
        final var questionsFromAnswers = answers.stream()
                .map(AnswerModel::getOption)
                .map(AnswerOptionModel::getNextQuestion)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (questionsFromAnswers.isEmpty()) {
            return getSummarizeQuestions(quiz);
        }

        return questionsFromAnswers;
    }

    private List<QuestionModel> getSummarizeQuestions(final QuizModel quiz) {
        final var summarizeQuestion = questionRepository.findSummarizeQuestion(quiz.getCategory());
        if (summarizeQuestion != null) {
            final var isAdded = quiz.getItems()
                    .stream()
                    .map(QuizItemModel::getQuestion)
                    .anyMatch(e -> e.getId().equals(summarizeQuestion.getId()));

            return isAdded ? Collections.emptyList() : Collections.singletonList(summarizeQuestion);

        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public QuizModel revertToPreviousQuestion(final QuizModel quiz) {
        Objects.requireNonNull(quiz);

        if (quiz.getCurrentStep() == 0) {
            return quiz;
        }

        final var previousStep = quiz.getCurrentStep() - 1;
        final var previousItem = quiz.getItems()
                .stream().filter(i -> i.getOrder() == previousStep)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The previous question was not found. QuizId=" + quiz.getId()));

        final var nextQuestionsIds = CollectionUtils.emptyIfNull(previousItem.getAnswers())
                .stream().map(AnswerModel::getOption)
                .map(AnswerOptionModel::getNextQuestion)
                .map(ItemModel::getId)
                .collect(Collectors.toList());

        final var itemsForRemoving = quiz.getItems().stream()
                .filter(item -> nextQuestionsIds.contains(item.getQuestion().getId()) || item.getOrder() == quiz.getCurrentStep())
                .collect(Collectors.toList());

        quizItemRepository.deleteAll(itemsForRemoving);

        final var newQuizItems = quiz.getItems().stream()
                .filter(item -> !nextQuestionsIds.contains(item.getQuestion().getId()) || item.getOrder() == previousStep)
                .sorted(Comparator.comparingInt(QuizItemModel::getOrder))
                .collect(Collectors.toList());

        recalculateItemOrder(newQuizItems, quiz);

        save(quiz);

        return quiz;
    }

    private void recalculateItemOrder(final List<QuizItemModel> newQuizItems, QuizModel quiz) {
        int order = 0;
        for (QuizItemModel newQuizItem : newQuizItems) {
            order++;
            newQuizItem.setOrder(order);
        }

        quiz.setItems(newQuizItems);
        quiz.setCurrentStep(quiz.getCurrentStep() - 1);
    }

    @Override
    protected JpaRepository<QuizModel, Integer> getItemRepository() {
        return quizRepository;
    }
}
