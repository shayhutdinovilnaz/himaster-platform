package ee.himaster.platform.facades.populator;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.dto.AnswerType;
import ee.himaster.platform.services.model.quiz.answer.AnswerModel;
import ee.himaster.platform.services.model.quiz.answer.InputNumericAnswerModel;
import ee.himaster.platform.services.model.quiz.answer.InputStringAnswerModel;
import ee.himaster.platform.services.model.quiz.answer.option.*;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class AbstractAnswerPopulator<T extends AnswerDto, S extends AnswerModel> implements Populator<T, S> {
    private final Converter<AnswerOptionDto, AnswerOptionModel> answerOptionConverter;

    @Override
    public T populate(S source, T target) {
        target.setId(source.getId());

        if (source.getOption() != null) {
            target.setOption(answerOptionConverter.convert(source.getOption()));
        }
        target.setType(getAnswerType(source.getOption()));
        target.setValue(getValue(target.getType(), source));
        return target;
    }

    private String getValue(final AnswerType type, final AnswerModel source) {
        if (type == AnswerType.INPUT_NUMERIC) {
            return Integer.toString(((InputNumericAnswerModel) source).getValue());
        } else if (type == AnswerType.INPUT_STRING) {
            return ((InputStringAnswerModel) source).getValue();
        }

        return null;
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

    @Override
    public S reversePopulate(final T source, final S target) {
        target.setId(source.getId());
        Optional.ofNullable(source.getOption()).map(AnswerOptionDto::getId).map()
        target.setOption(source.getOption());
        return null;
    }

    private AnswerOptionModel getAnswerOption(final T source) {
        return null;
    }
}
