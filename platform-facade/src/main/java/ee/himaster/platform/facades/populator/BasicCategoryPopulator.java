package ee.himaster.platform.facades.populator;

import ee.himaster.core.localization.service.LocalizedStringService;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.CategoryDto;
import ee.himaster.platform.services.model.CategoryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BasicCategoryPopulator implements Populator<CategoryDto, CategoryModel> {
    private final LocalizedStringService localizedStringService;

    @Override
    public CategoryDto populate(CategoryModel source, CategoryDto target) {
        target.setId(source.getId());
        target.setName(localizedStringService.getLocalizedStringValue(source.getName()));
        return target;
    }

    @Override
    public CategoryModel reversePopulate(CategoryDto source, CategoryModel target) {
        target.setId(source.getId());
        target.setName(localizedStringService.addLocalizedStringValue(source.getName(), target.getName()));
        return target;
    }
}