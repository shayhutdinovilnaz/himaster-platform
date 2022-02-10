package ee.himaster.platform.web.application.configuration;

import ee.himaster.platform.dto.CategoryDto;
import ee.himaster.platform.services.model.CategoryModel;
import java.util.function.Supplier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(value = "ee.himaster.platform*")
@ComponentScan(value = "ee.himaster.platform*")
@EntityScan(value = "ee.himaster.platform.services.model*")
public class ApplicationConfiguration {

    @Bean
    Supplier<CategoryModel> categoryModelSupplier() {
        return CategoryModel::new;
    }

    @Bean
    Supplier<CategoryDto> categoryDtoSupplier() {
        return CategoryDto::new;
    }
}
