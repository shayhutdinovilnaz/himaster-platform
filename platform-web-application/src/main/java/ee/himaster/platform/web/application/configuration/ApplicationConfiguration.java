package ee.himaster.platform.web.application.configuration;

import ee.himaster.platform.dto.CategoryDto;
import ee.himaster.platform.services.model.CategoryModel;
import java.util.function.Supplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
