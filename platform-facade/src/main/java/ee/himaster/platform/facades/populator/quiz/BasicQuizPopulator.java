package ee.himaster.platform.facades.populator.quiz;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.CategoryDto;
import ee.himaster.platform.dto.QuizDto;
import ee.himaster.platform.dto.QuizItemDto;
import ee.himaster.platform.services.model.CategoryModel;
import ee.himaster.platform.services.model.quiz.QuizItemModel;
import ee.himaster.platform.services.model.quiz.QuizModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BasicQuizPopulator implements Populator<QuizDto, QuizModel> {
    private final Converter<CategoryDto, CategoryModel> categoryConverter;
    private final Converter<QuizItemDto, QuizItemModel> quizItemConverter;

    @Override
    public QuizDto populate(final QuizModel source, final QuizDto target) {
        target.setId(source.getId());
        target.setSessionId(source.getSessionId());
        target.setItems(convertQuizItems(source));
        target.setCategory(categoryConverter.convert(source.getCategory()));
        target.setCurrentStep(source.getCurrentStep());
        target.setUserId(source.getUserId());
        return target;
    }

    private List<QuizItemDto> convertQuizItems(final QuizModel source) {
        if (CollectionUtils.isEmpty(source.getItems())) {
            return Collections.emptyList();
        }

        return source.getItems().stream().map(quizItemConverter::convert).collect(Collectors.toList());
    }

    @Override
    public QuizModel reversePopulate(final QuizDto source, final QuizModel target) {
        //TODO method is not supported.
        return target;
    }
}
