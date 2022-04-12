package ee.himaster.platform.facades.populator.quiz.answer;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.dto.AnswerType;
import ee.himaster.platform.services.model.quiz.answer.AnswerModel;
import ee.himaster.platform.services.model.quiz.answer.option.AnswerOptionModel;

public abstract class AbstractAnswerPopulator<S extends AnswerModel> implements Populator<AnswerDto, S> {

    @Override
    public AnswerDto populate(S source, AnswerDto target) {
        target.setId(source.getId());

        if (source.getOption() != null) {
            target.setOption(getOptionConverter(source).convert(source.getOption()));
        }
        target.setType(getAnswerType(source.getOption()));
        target.setValue(getValue(source));
        return target;
    }

    protected abstract Converter<AnswerOptionDto, AnswerOptionModel> getOptionConverter(S answerModel);

    protected abstract String getValue(final S answerModel);

    protected abstract AnswerType getAnswerType(final AnswerOptionModel source);

    @Override
    public S reversePopulate(final AnswerDto source, final S target) {
        target.setId(source.getId());
        target.setOption(getAnswerOption(source));
        return target;
    }

    protected abstract AnswerOptionModel getAnswerOption(final AnswerDto source);
}
