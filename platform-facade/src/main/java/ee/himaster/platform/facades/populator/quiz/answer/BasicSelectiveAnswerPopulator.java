package ee.himaster.platform.facades.populator.quiz.answer;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.dto.AnswerType;
import ee.himaster.platform.services.model.quiz.answer.SelectiveAnswerModel;
import ee.himaster.platform.services.model.quiz.answer.option.AnswerOptionModel;
import ee.himaster.platform.services.model.quiz.answer.option.SelectiveBooleanAnswerOptionModel;
import ee.himaster.platform.services.model.quiz.answer.option.SelectiveNumericAnswerOptionModel;
import ee.himaster.platform.services.model.quiz.answer.option.SelectiveStringAnswerOptionModel;
import ee.himaster.platform.services.service.AnswerOptionService;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BasicSelectiveAnswerPopulator extends AbstractAnswerPopulator<SelectiveAnswerModel> {
    private final Map<AnswerType, Converter<AnswerOptionDto, AnswerOptionModel>> optionConverterMap;
    private final Map<AnswerType, AnswerOptionService<AnswerOptionModel>> optionServiceMap;

    @Override
    protected Converter<AnswerOptionDto, AnswerOptionModel> getOptionConverter(final SelectiveAnswerModel answerModel) {
        final var answerType = getAnswerType(answerModel.getOption());
        return optionConverterMap.get(answerType);
    }

    @Override
    protected String getValue(final SelectiveAnswerModel answerModel) {
        return null;
    }

    @Override
    protected AnswerType getAnswerType(AnswerOptionModel source) {
        if (source instanceof SelectiveBooleanAnswerOptionModel) {
            return AnswerType.SELECTIVE_BOOLEAN;
        } else if (source instanceof SelectiveStringAnswerOptionModel) {
            return AnswerType.SELECTIVE_STRING;
        } else if (source instanceof SelectiveNumericAnswerOptionModel) {
            return AnswerType.SELECTIVE_NUMBERIC;
        }

        return null;
    }

    @Override
    protected AnswerOptionModel getAnswerOption(final AnswerDto source) {
        return optionServiceMap.get(source.getType()).getById(source.getOption().getId());
    }
}
