package ee.himaster.platform.facades.populator.quiz.answer;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.dto.AnswerType;
import ee.himaster.platform.services.model.quiz.answer.InputStringAnswerModel;
import ee.himaster.platform.services.model.quiz.answer.option.AnswerOptionModel;
import ee.himaster.platform.services.service.AnswerOptionService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BasicInputStringAnswerPopulator extends AbstractAnswerPopulator<InputStringAnswerModel> {

    public BasicInputStringAnswerPopulator(Map<AnswerType, Converter<AnswerOptionDto, AnswerOptionModel>> optionConverterMap,
                                           Map<AnswerType, AnswerOptionService<AnswerOptionModel>> optionServiceMap) {
        super(optionConverterMap, optionServiceMap);
    }

    @Override
    protected String getValue(InputStringAnswerModel answerModel) {
        return answerModel.getValue();
    }

    @Override
    protected AnswerType getAnswerType(AnswerOptionModel source) {
        return AnswerType.INPUT_STRING;
    }

    @Override
    public InputStringAnswerModel reversePopulate(AnswerDto source, InputStringAnswerModel target) {
        super.reversePopulate(source, target);
        target.setValue(source.getValue());
        return target;
    }
}
