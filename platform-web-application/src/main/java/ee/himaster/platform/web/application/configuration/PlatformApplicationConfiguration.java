package ee.himaster.platform.web.application.configuration;

import ee.himaster.core.localization.model.LocaleModel;
import ee.himaster.core.service.converter.impl.BasicConverter;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.CategoryDto;
import ee.himaster.platform.dto.CityDto;
import ee.himaster.platform.dto.LanguageDto;
import ee.himaster.platform.dto.LocaleDto;
import ee.himaster.platform.services.model.CategoryModel;
import ee.himaster.platform.services.model.CityModel;
import ee.himaster.platform.services.model.LanguageModel;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

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
    BasicConverter<LocaleDto, LocaleModel> la(List<Populator<LocaleDto, LocaleModel>> populators) {
        return new BasicConverter<>(populators, LocaleDto::new, LocaleModel::new);
    }
}
