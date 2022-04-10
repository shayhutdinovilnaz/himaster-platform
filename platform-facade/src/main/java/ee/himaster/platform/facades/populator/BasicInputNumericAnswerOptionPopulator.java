package ee.himaster.platform.facades.populator;

import ee.himaster.core.localization.service.LocalizedStringService;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.dto.AnswerType;
import ee.himaster.platform.services.model.quiz.answer.option.InputNumericAnswerOptionModel;
import org.springframework.stereotype.Component;

@Component
public class BasicInputNumericAnswerOptionPopulator extends AbstractAnswerOptionPopulator<AnswerOptionDto, InputNumericAnswerOptionModel> {
    public BasicInputNumericAnswerOptionPopulator(LocalizedStringService localizedStringService) {
        super(localizedStringService);
    }

    @Override
    protected AnswerType getAnswerType(InputNumericAnswerOptionModel source) {
        return AnswerType.INPUT_NUMERIC;
    }

    @Override
    protected String getValue(InputNumericAnswerOptionModel source) {
        return null;
    }

    @Override
    protected String getTitle(InputNumericAnswerOptionModel source) {
        return null;
    }
}
