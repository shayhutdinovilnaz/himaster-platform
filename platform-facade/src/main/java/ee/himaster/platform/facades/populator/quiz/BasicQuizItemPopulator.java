package ee.himaster.platform.facades.populator.quiz;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.dto.QuestionDto;
import ee.himaster.platform.dto.QuizItemDto;
import ee.himaster.platform.services.model.quiz.QuestionModel;
import ee.himaster.platform.services.model.quiz.QuizItemModel;
import ee.himaster.platform.services.model.quiz.answer.AnswerModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class BasicQuizItemPopulator implements Populator<QuizItemDto, QuizItemModel> {
    private final Converter<QuestionDto, QuestionModel> questionConverter;
    private final Converter<AnswerDto, AnswerModel> answerConverter;

    @Override
    public QuizItemDto populate(final QuizItemModel source, final QuizItemDto target) {
        target.setId(source.getId());
        target.setOrder(source.getStep());
        target.setAnswers(convertAnswers(source));
        target.setQuestion(questionConverter.convert(source.getQuestion()));
        return target;
    }

    private List<AnswerDto> convertAnswers(final QuizItemModel source) {
        if (source.getAnswers() != null) {

            return source.getAnswers().stream()
                    .map(answerConverter::convert)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @Override
    public QuizItemModel reversePopulate(final QuizItemDto source, final QuizItemModel target) {
        //TODO method is not supported.
        return target;
    }
}
