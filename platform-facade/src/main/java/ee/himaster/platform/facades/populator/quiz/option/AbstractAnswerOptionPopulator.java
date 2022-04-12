package ee.himaster.platform.facades.populator.quiz.option;

import ee.himaster.core.localization.service.LocalizedStringService;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.dto.AnswerType;
import ee.himaster.platform.services.model.quiz.answer.option.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractAnswerOptionPopulator<T extends AnswerOptionDto, S extends AnswerOptionModel> implements Populator<T, S> {
    protected final LocalizedStringService localizedStringService;

    @Override
    public T populate(final S source, final T target) {
        target.setId(source.getId());
        target.setLabel(localizedStringService.getLocalizedStringValue(source.getLabel()));
        target.setType(getAnswerType(source));
        target.setPriority(source.getPriority());
        target.setQualifier(source.getQualifier());
        target.setValue(getValue(source));
        target.setTitle(getTitle(source));
        return target;
    }

    protected abstract AnswerType getAnswerType(final S source);

    protected abstract String getValue(final S source);

    protected abstract String getTitle(final S source);

    @Override
    public S reversePopulate(final T source, final S target) {
        return target;
    }
}
