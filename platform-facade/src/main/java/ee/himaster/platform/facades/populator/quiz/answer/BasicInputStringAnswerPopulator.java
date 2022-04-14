package ee.himaster.platform.facades.populator.quiz.answer;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.dto.AnswerType;
import ee.himaster.platform.services.model.quiz.answer.InputStringAnswerModel;
import ee.himaster.platform.services.model.quiz.answer.option.AnswerOptionModel;
import ee.himaster.platform.services.model.quiz.answer.option.InputStringAnswerOptionModel;
import ee.himaster.platform.services.service.AnswerOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BasicInputStringAnswerPopulator extends AbstractAnswerPopulator<InputStringAnswerModel> {
    private final Converter<AnswerOptionDto, AnswerOptionModel> optionConverter;
    private final AnswerOptionService<InputStringAnswerOptionModel> optionService;

    @Override
    protected Converter<AnswerOptionDto, AnswerOptionModel> getOptionConverter(InputStringAnswerModel answerModel) {
        return optionConverter;
    }

    @Override
    protected String getValue(InputStringAnswerModel answerModel) {
        return answerModel.getValue();
    }

    @Override
    protected AnswerType getAnswerType(AnswerOptionModel source) {
        return AnswerType.INPUT_STRING;
    }

    @Override
    protected AnswerOptionModel getAnswerOption(AnswerDto source) {
        return optionService.getById(source.getOption().getId());
    }

    @Override
    public InputStringAnswerModel reversePopulate(AnswerDto source, InputStringAnswerModel target) {
        super.reversePopulate(source, target);
        target.setValue(source.getValue());
        return target;
    }
}
