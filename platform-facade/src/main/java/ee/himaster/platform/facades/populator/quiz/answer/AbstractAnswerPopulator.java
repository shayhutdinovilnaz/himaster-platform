package ee.himaster.platform.facades.populator.quiz.answer;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.services.model.quiz.answer.AnswerModel;
import ee.himaster.platform.services.model.quiz.answer.AnswerOptionModel;
import ee.himaster.platform.services.service.AnswerOptionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractAnswerPopulator<S extends AnswerModel> implements Populator<AnswerDto, S> {
    private final AnswerOptionService optionService;
    private final Converter<AnswerOptionDto, AnswerOptionModel> optionConverter;

    @Override
    public AnswerDto populate(S source, AnswerDto target) {
        target.setId(source.getId());

        if (source.getOption() != null) {
            final var optionDto = optionConverter.convert(source.getOption());
            target.setOption(optionDto);
        }
        target.setValue(getValue(source));
        return target;
    }

    protected abstract String getValue(final S answerModel);

    @Override
    public S reversePopulate(final AnswerDto source, final S target) {
        target.setId(source.getId());
        target.setOption(optionService.getById(source.getOption().getId()));
        return target;
    }
}
