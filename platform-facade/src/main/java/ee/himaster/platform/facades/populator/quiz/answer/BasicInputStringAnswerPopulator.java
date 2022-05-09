package ee.himaster.platform.facades.populator.quiz.answer;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.services.model.quiz.answer.AnswerOptionModel;
import ee.himaster.platform.services.model.quiz.answer.InputStringAnswerModel;
import ee.himaster.platform.services.service.AnswerOptionService;
import org.springframework.stereotype.Component;

@Component
public class BasicInputStringAnswerPopulator extends AbstractAnswerPopulator<InputStringAnswerModel> {

    public BasicInputStringAnswerPopulator(AnswerOptionService optionService, Converter<AnswerOptionDto, AnswerOptionModel> optionConverter) {
        super(optionService, optionConverter);
    }

    @Override
    protected String getValue(InputStringAnswerModel answerModel) {
        return answerModel.getValue();
    }

    @Override
    public InputStringAnswerModel reversePopulate(AnswerDto source, InputStringAnswerModel target) {
        super.reversePopulate(source, target);
        target.setValue(source.getValue());
        return target;
    }
}
