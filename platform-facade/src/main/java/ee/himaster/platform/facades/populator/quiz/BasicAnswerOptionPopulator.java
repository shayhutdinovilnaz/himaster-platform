package ee.himaster.platform.facades.populator.quiz;

import ee.himaster.core.localization.service.LocalizedStringService;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.services.model.quiz.answer.AnswerOptionModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BasicAnswerOptionPopulator implements Populator<AnswerOptionDto, AnswerOptionModel> {
    protected final LocalizedStringService localizedStringService;

    @Override
    public AnswerOptionDto populate(AnswerOptionModel source, AnswerOptionDto target) {
        target.setId(source.getId());
        target.setValue(localizedStringService.getLocalizedStringValue(source.getLabel()));
        target.setPriority(source.getPriority());
        return target;
    }

    @Override
    public AnswerOptionModel reversePopulate(AnswerOptionDto source, AnswerOptionModel target) {
        return null;
    }
}
