package ee.himaster.categories.populator;

import ee.himaster.categories.dto.CategoryDto;
import ee.himaster.categories.model.CategoryModel;
import ee.himaster.core.service.converter.impl.BasicConverter;
import ee.himaster.core.service.populator.Populator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BasicCategoryPopulator implements Populator<CategoryDto, CategoryModel> {
    private final BasicConverter<CategoryDto, CategoryModel> categoryConverter;

    @Override
    public CategoryDto populate(CategoryModel source, CategoryDto target) {
        target.setId(source.getId().intValue());
        target.setName(source.getName());
        return target;
    }

    @Override
    public CategoryModel reversePopulate(CategoryDto source, CategoryModel target) {
        target.setId(target.getId());
        target.setName(source.getName());
        return target;
    }
}
