package ee.himaster.platform.facades.populator.quiz.option;

import ee.himaster.core.localization.service.LocalizedStringService;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.dto.AnswerType;
import ee.himaster.platform.services.model.quiz.answer.option.SelectiveNumericAnswerOptionModel;
import org.springframework.stereotype.Component;

@Component
public class BasicSelectiveNumericAnswerOptionPopulator extends AbstractAnswerOptionPopulator<AnswerOptionDto, SelectiveNumericAnswerOptionModel> {
    public BasicSelectiveNumericAnswerOptionPopulator(LocalizedStringService localizedStringService) {
        super(localizedStringService);
    }

    @Override
    protected AnswerType getAnswerType(SelectiveNumericAnswerOptionModel source) {
        return AnswerType.SELECTIVE_NUMERIC;
    }

    @Override
    protected String getValue(SelectiveNumericAnswerOptionModel source) {
        return Integer.toString(source.getValue());
    }

    @Override
    protected String getTitle(SelectiveNumericAnswerOptionModel source) {
        return null;
    }
}
