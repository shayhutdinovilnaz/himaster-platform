package ee.himaster.categories.config;

import ee.himaster.categories.dto.CategoryDto;
import ee.himaster.categories.model.CategoryModel;
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
