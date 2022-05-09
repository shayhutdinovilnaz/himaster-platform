package ee.himaster.platform.facades.populator.quiz.answer;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.services.model.quiz.answer.AnswerOptionModel;
import ee.himaster.platform.services.model.quiz.answer.SelectiveAnswerModel;
import ee.himaster.platform.services.service.AnswerOptionService;
import org.springframework.stereotype.Component;

@Component
public class BasicSelectiveAnswerPopulator extends AbstractAnswerPopulator<SelectiveAnswerModel> {

    public BasicSelectiveAnswerPopulator(AnswerOptionService optionService, Converter<AnswerOptionDto, AnswerOptionModel> optionConverter) {
        super(optionService, optionConverter);
    }

    @Override
    protected String getValue(final SelectiveAnswerModel answerModel) {
        return null;
    }
}
