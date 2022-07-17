package ee.himaster.platform.facades.populator.quiz.answer;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.services.model.quiz.answer.AnswerModel;
import ee.himaster.platform.services.model.quiz.answer.AnswerOptionModel;
import ee.himaster.platform.services.service.AnswerOptionService;
import ee.himaster.platform.services.service.AnswerService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AnswerPopulator implements Populator<AnswerDto, AnswerModel> {
    private final AnswerOptionService optionService;
    private final AnswerService answerService;
    private final Converter<AnswerOptionDto, AnswerOptionModel> optionConverter;

    @Override
    public AnswerDto populate(AnswerModel source, AnswerDto target) {
        target.setId(source.getId());

        if (source.getOption() != null) {
            final var optionDto = optionConverter.convert(source.getOption());
            target.setOption(optionDto);
        }

        target.setValue(answerService.getAnswerAsString(source));
        return target;
    }

    @Override
    public AnswerModel reversePopulate(final AnswerDto source, final AnswerModel target) {
        target.setId(source.getId());
        target.setOption(optionService.getById(source.getOption().getId()));
        target.setValues(answerService.getAnswerFromString(source.getValue()));
        return target;
    }
}
