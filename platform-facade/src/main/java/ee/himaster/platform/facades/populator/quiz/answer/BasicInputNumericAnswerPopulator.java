package ee.himaster.platform.facades.populator.quiz.answer;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.dto.AnswerType;
import ee.himaster.platform.services.model.quiz.answer.InputNumericAnswerModel;
import ee.himaster.platform.services.model.quiz.answer.option.AnswerOptionModel;
import ee.himaster.platform.services.service.AnswerOptionService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BasicInputNumericAnswerPopulator extends AbstractAnswerPopulator<InputNumericAnswerModel> {

    public BasicInputNumericAnswerPopulator(Map<AnswerType, Converter<AnswerOptionDto, AnswerOptionModel>> optionConverterMap,
                                            Map<AnswerType, AnswerOptionService<AnswerOptionModel>> optionServiceMap) {
        super(optionConverterMap, optionServiceMap);
    }

    @Override
    protected String getValue(final InputNumericAnswerModel answerModel) {
        return Integer.toString(answerModel.getValue());
    }

    @Override
    protected AnswerType getAnswerType(final AnswerOptionModel source) {
        return AnswerType.INPUT_NUMERIC;
    }

    @Override
    public InputNumericAnswerModel reversePopulate(AnswerDto source, InputNumericAnswerModel target) {
        super.reversePopulate(source, target);

        if (source.getValue() != null) {
            target.setValue(Integer.valueOf(source.getValue()));
        }

        return target;
    }
}
