package ee.himaster.platform.facades.populator;

import ee.himaster.core.localization.service.LocalizedStringService;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.dto.AnswerType;
import ee.himaster.platform.services.model.quiz.answer.option.AnswerOptionModel;
import ee.himaster.platform.services.model.quiz.answer.option.InputNumericAnswerOptionModel;
import ee.himaster.platform.services.model.quiz.answer.option.InputStringAnswerOptionModel;
import ee.himaster.platform.services.model.quiz.answer.option.SelectiveBooleanAnswerOptionModel;
import ee.himaster.platform.services.model.quiz.answer.option.SelectiveNumericAnswerOptionModel;
import ee.himaster.platform.services.model.quiz.answer.option.SelectiveStringAnswerOptionModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BasicAnswerOptionPopulator implements Populator<AnswerOptionDto, AnswerOptionModel> {
    private final LocalizedStringService localizedStringService;

    @Override
    public AnswerOptionDto populate(final AnswerOptionModel source, final AnswerOptionDto target) {
        target.setId(source.getId());
        target.setLabel(localizedStringService.getLocalizedStringValue(source.getLabel()));
        target.setType(getAnswerType(source));
        target.setPriority(source.getPriority());
        target.setQualifier(source.getQualifier());
        target.setValue(getValue(source));
        target.setTitle(getTitle(source));
        return target;
    }

    private AnswerType getAnswerType(final AnswerOptionModel source) {
        if (source instanceof InputNumericAnswerOptionModel) {
            return AnswerType.INPUT_NUMERIC;
        } else if (source instanceof InputStringAnswerOptionModel) {
            return AnswerType.INPUT_STRING;
        } else if (source instanceof SelectiveNumericAnswerOptionModel) {
            return AnswerType.SELECTIVE_NUMBERIC;
        } else if (source instanceof SelectiveBooleanAnswerOptionModel) {
            return AnswerType.SELECTIVE_BOOLEAN;
        } else if (source instanceof SelectiveStringAnswerOptionModel) {
            return AnswerType.SELECTIVE_STRING;
        }
        return null;
    }

    private String getValue(final AnswerOptionModel source) {

        if (source instanceof SelectiveNumericAnswerOptionModel) {
            return Integer.toString(((SelectiveNumericAnswerOptionModel) source).getValue());
        } else if (source instanceof SelectiveBooleanAnswerOptionModel) {
            return Boolean.toString(((SelectiveBooleanAnswerOptionModel) source).getValue());
        } else if (source instanceof SelectiveStringAnswerOptionModel) {
            return localizedStringService.getLocalizedStringValue(((SelectiveStringAnswerOptionModel) source).getValue());
        }

        return null;
    }

    private String getTitle(final AnswerOptionModel source) {
        if (source instanceof SelectiveBooleanAnswerOptionModel) {
            return localizedStringService.getLocalizedStringValue(((SelectiveBooleanAnswerOptionModel) source).getTitle());
        }
        return null;
    }


    @Override
    public AnswerOptionModel reversePopulate(AnswerOptionDto source, AnswerOptionModel target) {
        return null;
    }
}
