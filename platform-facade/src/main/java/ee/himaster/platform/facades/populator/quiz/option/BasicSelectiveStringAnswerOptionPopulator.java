package ee.himaster.platform.facades.populator.quiz.option;

import ee.himaster.core.localization.service.LocalizedStringService;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.dto.AnswerType;
import ee.himaster.platform.services.model.quiz.answer.option.SelectiveStringAnswerOptionModel;
import org.springframework.stereotype.Component;

@Component
public class BasicSelectiveStringAnswerOptionPopulator extends AbstractAnswerOptionPopulator<AnswerOptionDto, SelectiveStringAnswerOptionModel> {
    public BasicSelectiveStringAnswerOptionPopulator(LocalizedStringService localizedStringService) {
        super(localizedStringService);
    }

    @Override
    protected AnswerType getAnswerType(SelectiveStringAnswerOptionModel source) {
        return AnswerType.SELECTIVE_STRING;
    }

    @Override
    protected String getValue(SelectiveStringAnswerOptionModel source) {
        return localizedStringService.getLocalizedStringValue(source.getValue());
    }

    @Override
    protected String getTitle(SelectiveStringAnswerOptionModel source) {
        return null;
    }
}
