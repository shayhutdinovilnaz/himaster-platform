package ee.himaster.platform.facades.facade.impl;

import ee.himaster.core.service.converter.impl.BasicConverter;
import ee.himaster.platform.dto.CategoryDto;
import ee.himaster.platform.facades.facade.CategoryFacade;
import ee.himaster.platform.services.model.CategoryModel;
import ee.himaster.platform.services.service.CategoryService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultCategoryFacade implements CategoryFacade {
    private final CategoryService categoryService;
    private final BasicConverter<CategoryDto, CategoryModel> categoryConverter;


    @Override
    public CategoryDto getById(Integer categoryId) {
        return categoryConverter.convert(categoryService.getCategoryById(categoryId));
    }

    @Override
    public List<CategoryDto> getRootCategories() {
        return CollectionUtils.emptyIfNull(categoryService.getRootCategories())
                .stream()
                .map(categoryConverter::convert)
                .collect(Collectors.toList());
    }
}
