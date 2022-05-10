package ee.himaster.platform.web.application.configuration;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.core.service.converter.impl.BasicConverter;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.*;
import ee.himaster.platform.facades.converter.LocaleConverter;
import ee.himaster.platform.services.model.CategoryModel;
import ee.himaster.platform.services.model.CityModel;
import ee.himaster.platform.services.model.LanguageModel;
import ee.himaster.platform.services.model.quiz.QuestionComponentType;
import ee.himaster.platform.services.model.quiz.QuestionModel;
import ee.himaster.platform.services.model.quiz.QuizItemModel;
import ee.himaster.platform.services.model.quiz.QuizModel;
import ee.himaster.platform.services.model.quiz.answer.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Map;

import static ee.himaster.platform.services.model.quiz.QuestionComponentType.*;

@Configuration
@EnableJpaRepositories(value = "ee.himaster.platform*")
@ComponentScan(value = "ee.himaster.platform*")
@EntityScan(value = "ee.himaster.platform.services.model*")
public class PlatformApplicationConfiguration {

    @Bean
    BasicConverter<CategoryDto, CategoryModel> categoryConverter(List<Populator<CategoryDto, CategoryModel>> populators) {
        return new BasicConverter<>(populators, CategoryDto::new, CategoryModel::new);
    }

    @Bean
    BasicConverter<LanguageDto, LanguageModel> languageConverter(List<Populator<LanguageDto, LanguageModel>> populators) {
        return new BasicConverter<>(populators, LanguageDto::new, LanguageModel::new);
    }

    @Bean
    BasicConverter<CityDto, CityModel> cityConverter(List<Populator<CityDto, CityModel>> populators) {
        return new BasicConverter<>(populators, CityDto::new, CityModel::new);
    }

    @Bean
    BasicConverter<QuestionDto, QuestionModel> questionConverter(List<Populator<QuestionDto, QuestionModel>> populators) {
        return new BasicConverter<>(populators, QuestionDto::new, QuestionModel::new);
    }

    @Bean
    BasicConverter<QuizItemDto, QuizItemModel> quizItemConverter(List<Populator<QuizItemDto, QuizItemModel>> populators) {
        return new BasicConverter<>(populators, QuizItemDto::new, QuizItemModel::new);
    }

    @Bean
    BasicConverter<QuizDto, QuizModel> quizConverter(List<Populator<QuizDto, QuizModel>> populators) {
        return new BasicConverter<>(populators, QuizDto::new, QuizModel::new);
    }

    @Bean
    BasicConverter<AnswerDto, InputNumericAnswerModel> inputNumericAnswerConverter(List<Populator<AnswerDto, InputNumericAnswerModel>> populators) {
        return new BasicConverter<>(populators, AnswerDto::new, InputNumericAnswerModel::new);
    }

    @Bean
    BasicConverter<AnswerDto, InputStringAnswerModel> inputStringAnswerConverter(List<Populator<AnswerDto, InputStringAnswerModel>> populators) {
        return new BasicConverter<>(populators, AnswerDto::new, InputStringAnswerModel::new);
    }

    @Bean
    BasicConverter<AnswerDto, SelectiveAnswerModel> selectiveAnswerConverter(List<Populator<AnswerDto, SelectiveAnswerModel>> populators) {
        return new BasicConverter<>(populators, AnswerDto::new, SelectiveAnswerModel::new);
    }

    @Bean
    BasicConverter<AnswerOptionDto, AnswerOptionModel> answerOptionConverter(List<Populator<AnswerOptionDto, AnswerOptionModel>> populators) {
        return new BasicConverter<>(populators, AnswerOptionDto::new, AnswerOptionModel::new);
    }

    @Bean
    Map<QuestionComponentType, Converter<AnswerDto, ? extends AnswerModel >> answerConverters(
            Converter<AnswerDto, InputNumericAnswerModel> inputNumericAnswerConverter,
            Converter<AnswerDto, InputStringAnswerModel> inputStringAnswerConverter,
            Converter<AnswerDto, SelectiveAnswerModel> selectiveAnswerConverter) {
        return Map.of(
                CHECKBOX, selectiveAnswerConverter,
                COMBO_BOX, selectiveAnswerConverter,
                LIST_BOX, selectiveAnswerConverter,
                NUMBER_FIELD, inputNumericAnswerConverter,
                RADIO_BUTTON, selectiveAnswerConverter,
                TEXT_AREA, inputStringAnswerConverter,
                TEXT_FIELD, inputStringAnswerConverter);
    }

    @Bean
    LocaleConverter localeConverter() {
        return new LocaleConverter();
    }
}
