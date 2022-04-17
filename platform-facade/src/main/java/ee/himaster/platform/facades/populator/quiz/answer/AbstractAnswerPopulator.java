package ee.himaster.platform.facades.populator.quiz.answer;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.dto.AnswerType;
import ee.himaster.platform.services.model.quiz.answer.AnswerModel;
import ee.himaster.platform.services.model.quiz.answer.option.AnswerOptionModel;
import ee.himaster.platform.services.service.AnswerOptionService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public abstract class AbstractAnswerPopulator<S extends AnswerModel> implements Populator<AnswerDto, S> {

    private final Map<AnswerType, Converter<AnswerOptionDto, AnswerOptionModel>> optionConverterMap;
    private final Map<AnswerType, AnswerOptionService<AnswerOptionModel>> optionServiceMap;

    @Override
    public AnswerDto populate(S source, AnswerDto target) {
        target.setId(source.getId());

        if (source.getOption() != null) {
            final var optionDto = optionConverterMap.get(getAnswerType(source.getOption())).convert(source.getOption());
            target.setOption(optionDto);
        }
        target.setType(getAnswerType(source.getOption()));
        target.setValue(getValue(source));
        return target;
    }

    protected abstract String getValue(final S answerModel);

    protected abstract AnswerType getAnswerType(final AnswerOptionModel source);

    @Override
    public S reversePopulate(final AnswerDto source, final S target) {
        target.setId(source.getId());
        final var optionDto = optionServiceMap.get(source.getType()).getById(source.getOption().getId());
        target.setOption(optionDto);
        return target;
    }
}
