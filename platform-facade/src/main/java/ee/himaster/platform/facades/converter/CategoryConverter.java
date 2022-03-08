package ee.himaster.platform.facades.converter;

import ee.himaster.core.service.converter.impl.BasicConverter;
import ee.himaster.core.service.model.ItemModel;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.CategoryDto;
import ee.himaster.platform.services.model.CategoryModel;
import ee.himaster.platform.services.service.CategoryService;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter extends BasicConverter<CategoryDto, CategoryModel> {
    private final CategoryService categoryService;

    public CategoryConverter(List<Populator<CategoryDto, CategoryModel>> populators,
            Supplier<CategoryDto> categoryDtoSupplier,
            Supplier<CategoryModel> categoryModelSupplier,
            CategoryService categoryService) {
        super(populators, categoryDtoSupplier, categoryModelSupplier);
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto convert(CategoryModel source) {
        final var target = super.convert(source);

        if (source.getParentCategory() != null) {
            target.setParent(source.getParentCategory().getId());
        }

        if (CollectionUtils.isNotEmpty(source.getChildrenCategories())) {
            final List<Integer> childrenCategories = source.getChildrenCategories()
                    .stream()
                    .map(ItemModel::getId)
                    .collect(Collectors.toList());
            target.setChildren(childrenCategories);
        }

        return target;
    }

    @Override
    public CategoryModel reverseConvert(CategoryDto source) {
        final var target = super.reverseConvert(source);

        if (source.getParent() != null) {
            target.setParentCategory(categoryService.getById(source.getParent()));
        }

        if (CollectionUtils.isNotEmpty(source.getChildren())) {
            final List<CategoryModel> childrenCategories = source.getChildren()
                    .stream()
                    .map(categoryService::getById)
                    .collect(Collectors.toList());
            target.setChildrenCategories(childrenCategories);
        }

        return target;
    }
}
