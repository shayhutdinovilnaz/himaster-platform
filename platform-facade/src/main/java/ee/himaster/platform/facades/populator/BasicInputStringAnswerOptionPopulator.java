package ee.himaster.platform.facades.populator;

import ee.himaster.core.localization.service.LocalizedStringService;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.dto.AnswerType;
import ee.himaster.platform.services.model.quiz.answer.option.InputStringAnswerOptionModel;
import org.springframework.stereotype.Component;

@Component
public class BasicInputStringAnswerOptionPopulator extends AbstractAnswerOptionPopulator<AnswerOptionDto, InputStringAnswerOptionModel> {
    public BasicInputStringAnswerOptionPopulator(LocalizedStringService localizedStringService) {
        super(localizedStringService);
    }

    @Override
    protected AnswerType getAnswerType(InputStringAnswerOptionModel source) {
        return AnswerType.INPUT_STRING;
    }

    @Override
    protected String getValue(InputStringAnswerOptionModel source) {
        return null;
    }

    @Override
    protected String getTitle(InputStringAnswerOptionModel source) {
        return null;
    }
}
