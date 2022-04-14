package ee.himaster.platform.facades.populator.quiz;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.dto.QuestionDto;
import ee.himaster.platform.dto.QuizItemDto;
import ee.himaster.platform.services.model.quiz.QuestionModel;
import ee.himaster.platform.services.model.quiz.QuizItemModel;
import ee.himaster.platform.services.model.quiz.answer.InputNumericAnswerModel;
import ee.himaster.platform.services.model.quiz.answer.InputStringAnswerModel;
import ee.himaster.platform.services.model.quiz.answer.SelectiveAnswerModel;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BasicQuizItemPopulator implements Populator<QuizItemDto, QuizItemModel> {
    private final Converter<QuestionDto, QuestionModel> questionConverter;
    private final Converter<AnswerDto, SelectiveAnswerModel> selectiveAnswerConverter;
    private final Converter<AnswerDto, InputStringAnswerModel> inputStringAnswerConverter;
    private final Converter<AnswerDto, InputNumericAnswerModel> inputNumericAnswerConverter;

    @Override
    public QuizItemDto populate(final QuizItemModel source, final QuizItemDto target) {
        target.setId(source.getId());
        target.setOrder(source.getOrder());
        target.setAnswers(convertAnswers(source));
        target.setQuestion(questionConverter.convert(source.getQuestion()));
        return target;
    }

    private List<AnswerDto> convertAnswers(QuizItemModel source) {
        return source.getAnswers().stream().map(answer -> {
            if (answer instanceof SelectiveAnswerModel) {
                return selectiveAnswerConverter.convert((SelectiveAnswerModel) answer);
            } else if (answer instanceof InputStringAnswerModel) {
                return inputStringAnswerConverter.convert((InputStringAnswerModel) answer);
            } else if (answer instanceof InputNumericAnswerModel) {
                return inputNumericAnswerConverter.convert((InputNumericAnswerModel) answer);
            }
            log.error("Could not convert answer. Type: {} ", answer.getClass().getSimpleName());
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public QuizItemModel reversePopulate(final QuizItemDto source, final QuizItemModel target) {
        //TODO method is not supported.
        return target;
    }
}
