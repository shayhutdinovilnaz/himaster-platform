package ee.himaster.platform.facades.populator.quiz.answer;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.dto.AnswerType;
import ee.himaster.platform.services.model.quiz.answer.InputNumericAnswerModel;
import ee.himaster.platform.services.model.quiz.answer.option.AnswerOptionModel;
import ee.himaster.platform.services.service.InputNumericAnswerOptionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BasicInputNumericAnswerPopulator extends AbstractAnswerPopulator<InputNumericAnswerModel> {
    private final Converter<AnswerOptionDto, AnswerOptionModel> optionConverter;
    private final InputNumericAnswerOptionService optionService;

    @Override
    protected Converter<AnswerOptionDto, AnswerOptionModel> getOptionConverter(final InputNumericAnswerModel answerModel) {
        return optionConverter;
    }

    @Override
    protected String getValue(final InputNumericAnswerModel answerModel) {
        return Integer.toString(answerModel.getValue());
    }

    @Override
    protected AnswerType getAnswerType(final AnswerOptionModel source) {
        return AnswerType.INPUT_NUMERIC;
    }

    @Override
    protected AnswerOptionModel getAnswerOption(final AnswerDto source) {
        return optionService.getById(source.getOption().getId());
    }

    @Override
    public InputNumericAnswerModel reversePopulate(AnswerDto source, InputNumericAnswerModel target) {
        super.reversePopulate(source, target);

        if (source.getValue() != null) {
            target.setValue(Integer.valueOf(source.getValue()));
        }

        return target;
    }
}
