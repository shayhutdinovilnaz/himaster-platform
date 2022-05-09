package ee.himaster.platform.facades.populator.quiz.answer;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.services.model.quiz.answer.AnswerOptionModel;
import ee.himaster.platform.services.model.quiz.answer.InputNumericAnswerModel;
import ee.himaster.platform.services.service.AnswerOptionService;
import org.springframework.stereotype.Component;

@Component
public class BasicInputNumericAnswerPopulator extends AbstractAnswerPopulator<InputNumericAnswerModel> {

    public BasicInputNumericAnswerPopulator(AnswerOptionService optionService, Converter<AnswerOptionDto, AnswerOptionModel> optionConverter) {
        super(optionService, optionConverter);
    }

    @Override
    protected String getValue(final InputNumericAnswerModel answerModel) {
        return Integer.toString(answerModel.getValue());
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
