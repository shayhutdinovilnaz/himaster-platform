package ee.himaster.platform.facades.populator;

import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.CategoryDto;
import ee.himaster.platform.services.model.CategoryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BasicCategoryPopulator implements Populator<CategoryDto, CategoryModel> {

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
