package ee.himaster.platform.facades.populator.quiz;

import ee.himaster.core.localization.service.LocalizedStringService;
import ee.himaster.core.service.converter.Converter;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.AnswerOptionDto;
import ee.himaster.platform.dto.AnswerType;
import ee.himaster.platform.dto.QuestionDto;
import ee.himaster.platform.dto.QuestionType;
import ee.himaster.platform.services.model.quiz.QuestionComponentType;
import ee.himaster.platform.services.model.quiz.QuestionModel;
import ee.himaster.platform.services.model.quiz.answer.option.*;
import ee.himaster.platform.services.service.AnswerOptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Component
public class BasicQuestionPopulator implements Populator<QuestionDto, QuestionModel> {
    private final LocalizedStringService localizedStringService;
    private final Map<AnswerType, AnswerOptionService<AnswerOptionModel>> optionServices;
    private final Map<AnswerType, Converter<AnswerOptionDto, AnswerOptionModel>> optionConverterMap;

    @Override
    public QuestionDto populate(final QuestionModel source, final QuestionDto target) {
        target.setId(source.getId());
        target.setTitle(localizedStringService.getLocalizedStringValue(source.getTitle()));
        target.setType(convertType(source.getType()));
        target.setAnswerOptions(getAnswerOptions(source));
        return target;
    }

    private List<AnswerOptionDto> getAnswerOptions(final QuestionModel questionModel) {
        if (CollectionUtils.isEmpty(questionModel.getAnswerOptions())) {
            return Collections.emptyList();
        }

        return questionModel
                .getAnswerOptions()
                .stream()
                .map(this::convertToAnswerOption)
                .collect(Collectors.toList());
    }

    private AnswerOptionDto convertToAnswerOption(final AnswerOptionModel answerOptionModel) {
        final var answerType = getAnswerType(answerOptionModel);
        return optionConverterMap.get(answerType).convert(answerOptionModel);
    }

    private AnswerType getAnswerType(final AnswerOptionModel answerOptionModel) {
        if (answerOptionModel instanceof InputNumericAnswerOptionModel) {
            return AnswerType.INPUT_NUMERIC;
        } else if (answerOptionModel instanceof InputStringAnswerOptionModel) {
            return AnswerType.INPUT_STRING;
        } else if (answerOptionModel instanceof SelectiveBooleanAnswerOptionModel) {
            return AnswerType.SELECTIVE_BOOLEAN;
        } else if (answerOptionModel instanceof SelectiveNumericAnswerOptionModel) {
            return AnswerType.SELECTIVE_NUMERIC;
        } else if (answerOptionModel instanceof SelectiveStringAnswerOptionModel) {
            return AnswerType.SELECTIVE_STRING;
        }

        return null;
    }

    private QuestionType convertType(final QuestionComponentType type) {

        switch (type) {
            case CHECKBOX:
                return QuestionType.CHECKBOX;
            case LIST_BOX:
                return QuestionType.LIST_BOX;
            case COMBO_BOX:
                return QuestionType.COMBO_BOX;
            case TEXT_AREA:
                return QuestionType.TEXT_AREA;
            case TEXT_FIELD:
                return QuestionType.TEXT_FIELD;
            case DATE_PICKER:
                return QuestionType.DATE_PICKER;
            case TIME_PICKER:
                return QuestionType.TIME_PICKER;
            case NUMBER_FIELD:
                return QuestionType.NUMBER_FIELD;
            case RADIO_BUTTON:
                return QuestionType.RADIO_BUTTON;
            case DATE_TIME_PICKER:
                return QuestionType.DATE_TIME_PICKER;
            default:
                log.error("The question component type is not available for converting. Question type: {}. ", type);
                throw new IllegalArgumentException("The question type is not available for converting. ");
        }
    }

    @Override
    public QuestionModel reversePopulate(QuestionDto source, QuestionModel target) {

        target.setId(source.getId());
        target.setTitle(localizedStringService.addLocalizedStringValue(source.getTitle(), target.getTitle()));
        target.setType(convertType(source.getType()));
        target.setAnswerOptions(getAnswerOptions(source.getAnswerOptions()));

        return target;
    }

    private List<AnswerOptionModel> getAnswerOptions(final List<AnswerOptionDto> answerOptions) {
        if (CollectionUtils.isEmpty(answerOptions)) {
            return Collections.emptyList();
        }

        return answerOptions
                .stream()
                .map(a -> optionServices.get(a.getType()).getById(a.getId()))
                .collect(Collectors.toList());
    }

    private QuestionComponentType convertType(final QuestionType type) {

        switch (type) {
            case CHECKBOX:
                return QuestionComponentType.CHECKBOX;
            case LIST_BOX:
                return QuestionComponentType.LIST_BOX;
            case COMBO_BOX:
                return QuestionComponentType.COMBO_BOX;
            case TEXT_AREA:
                return QuestionComponentType.TEXT_AREA;
            case TEXT_FIELD:
                return QuestionComponentType.TEXT_FIELD;
            case DATE_PICKER:
                return QuestionComponentType.DATE_PICKER;
            case TIME_PICKER:
                return QuestionComponentType.TIME_PICKER;
            case NUMBER_FIELD:
                return QuestionComponentType.NUMBER_FIELD;
            case RADIO_BUTTON:
                return QuestionComponentType.RADIO_BUTTON;
            case DATE_TIME_PICKER:
                return QuestionComponentType.DATE_TIME_PICKER;
            default:
                log.error("The question type is not available for converting. Question type: {}. ", type);
                throw new IllegalArgumentException("The question type is not available for converting. ");
        }
    }

}
