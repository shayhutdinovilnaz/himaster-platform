package ee.himaster.platform.web.application.configuration;

import ee.himaster.core.service.converter.Converter;
import ee.himaster.core.service.converter.impl.BasicConverter;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.*;
import ee.himaster.platform.facades.converter.LocaleConverter;
import ee.himaster.platform.services.model.CategoryModel;
import ee.himaster.platform.services.model.CityModel;
import ee.himaster.platform.services.model.LanguageModel;
import ee.himaster.platform.services.model.quiz.QuestionModel;
import ee.himaster.platform.services.model.quiz.QuizItemModel;
import ee.himaster.platform.services.model.quiz.QuizModel;
import ee.himaster.platform.services.model.quiz.answer.InputNumericAnswerModel;
import ee.himaster.platform.services.model.quiz.answer.InputStringAnswerModel;
import ee.himaster.platform.services.model.quiz.answer.SelectiveAnswerModel;
import ee.himaster.platform.services.model.quiz.answer.option.*;
import ee.himaster.platform.services.service.AnswerOptionService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Map;

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
    Converter<AnswerOptionDto, InputNumericAnswerOptionModel> inputNumericAnswerOptionConverter(List<Populator<AnswerOptionDto, InputNumericAnswerOptionModel>> populators) {
        return new BasicConverter<>(populators, AnswerOptionDto::new, InputNumericAnswerOptionModel::new);
    }

    @Bean
    Converter<AnswerOptionDto, InputStringAnswerOptionModel> inputStringAnswerOptionConverter(List<Populator<AnswerOptionDto, InputStringAnswerOptionModel>> populators) {
        return new BasicConverter<>(populators, AnswerOptionDto::new, InputStringAnswerOptionModel::new);
    }

    @Bean
    Converter<AnswerOptionDto, SelectiveBooleanAnswerOptionModel> selectiveBooleanAnswerOptionConverter(List<Populator<AnswerOptionDto, SelectiveBooleanAnswerOptionModel>> populators) {
        return new BasicConverter<>(populators, AnswerOptionDto::new, SelectiveBooleanAnswerOptionModel::new);
    }

    @Bean
    Converter<AnswerOptionDto, SelectiveNumericAnswerOptionModel> selectiveNumericAnswerOptionConverter(List<Populator<AnswerOptionDto, SelectiveNumericAnswerOptionModel>> populators) {
        return new BasicConverter<>(populators, AnswerOptionDto::new, SelectiveNumericAnswerOptionModel::new);
    }

    @Bean
    Converter<AnswerOptionDto, SelectiveStringAnswerOptionModel> selectiveStringAnswerOptionConverter(List<Populator<AnswerOptionDto, SelectiveStringAnswerOptionModel>> populators) {
        return new BasicConverter<>(populators, AnswerOptionDto::new, SelectiveStringAnswerOptionModel::new);
    }

    @Bean
    Map<AnswerType, Converter<AnswerOptionDto, AnswerOptionModel>> optionConverterMap(
            List<Populator<AnswerOptionDto, InputNumericAnswerOptionModel>> inputNumericAnswerOptionPopulators,
            List<Populator<AnswerOptionDto, InputStringAnswerOptionModel>> inputStringAnswerOptionPopulators,
            List<Populator<AnswerOptionDto, SelectiveBooleanAnswerOptionModel>> selectiveBooleanAnswerOptionPopulators,
            List<Populator<AnswerOptionDto, SelectiveNumericAnswerOptionModel>> selectiveNumericAnswerOptionPopulators,
            List<Populator<AnswerOptionDto, SelectiveStringAnswerOptionModel>> selectiveStringAnswerOptionPopulators) {
        //TODO
        return null;
    }

    @Bean
    Map<AnswerType, AnswerOptionService<AnswerOptionModel>> optionServiceMap(
            AnswerOptionService<InputNumericAnswerOptionModel> inputNumericAnswerOptionService,
            AnswerOptionService<InputStringAnswerOptionModel> inputStringAnswerOptionService,
            AnswerOptionService<SelectiveBooleanAnswerOptionModel> selectiveBooleanAnswerOptionService,
            AnswerOptionService<SelectiveNumericAnswerOptionModel> selectiveNumericAnswerOptionService,
            AnswerOptionService<SelectiveStringAnswerOptionModel> selectiveStringAnswerOptionService) {
        //TODO
        return null;
    }

    @Bean
    LocaleConverter localeConverter() {
        return new LocaleConverter();
    }
}
