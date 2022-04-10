package ee.himaster.platform.facades.populator;

import ee.himaster.core.localization.service.LocalizedStringService;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.dto.AnswerType;
import ee.himaster.platform.services.model.quiz.answer.option.SelectiveBooleanAnswerOptionModel;
import org.springframework.stereotype.Component;

@Component
public class BasicSelectiveBooleanAnswerOptionPopulator extends AbstractAnswerOptionPopulator<AnswerOptionDto, SelectiveBooleanAnswerOptionModel> {
    public BasicSelectiveBooleanAnswerOptionPopulator(LocalizedStringService localizedStringService) {
        super(localizedStringService);
    }

    @Override
    protected AnswerType getAnswerType(SelectiveBooleanAnswerOptionModel source) {
        return AnswerType.SELECTIVE_BOOLEAN;
    }

    @Override
    protected String getValue(SelectiveBooleanAnswerOptionModel source) {
        return Boolean.toString(source.getValue());
    }

    @Override
    protected String getTitle(SelectiveBooleanAnswerOptionModel source) {
        return localizedStringService.getLocalizedStringValue(source.getTitle());
    }
}
