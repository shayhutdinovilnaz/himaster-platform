package ee.himaster.platform.facades.populator.quiz.answer;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.dto.AnswerType;
import ee.himaster.platform.services.model.quiz.answer.SelectiveAnswerModel;
import ee.himaster.platform.services.model.quiz.answer.option.AnswerOptionModel;
import ee.himaster.platform.services.model.quiz.answer.option.SelectiveBooleanAnswerOptionModel;
import ee.himaster.platform.services.model.quiz.answer.option.SelectiveNumericAnswerOptionModel;
import ee.himaster.platform.services.model.quiz.answer.option.SelectiveStringAnswerOptionModel;
import ee.himaster.platform.services.service.AnswerOptionService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BasicSelectiveAnswerPopulator extends AbstractAnswerPopulator<SelectiveAnswerModel> {

    public BasicSelectiveAnswerPopulator(Map<AnswerType, Converter<AnswerOptionDto, AnswerOptionModel>> optionConverterMap,
                                         Map<AnswerType, AnswerOptionService<AnswerOptionModel>> optionServiceMap) {
        super(optionConverterMap, optionServiceMap);
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
            return AnswerType.SELECTIVE_NUMERIC;
        }

        return null;
    }
}
