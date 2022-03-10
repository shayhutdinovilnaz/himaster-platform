package ee.himaster.platform.facades.populator;

import ee.himaster.core.localization.service.LocalizedStringService;
import ee.himaster.core.service.model.ItemModel;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.CategoryDto;
import ee.himaster.platform.services.model.CategoryModel;
import ee.himaster.platform.services.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BasicCategoryPopulator implements Populator<CategoryDto, CategoryModel> {
    private final LocalizedStringService localizedStringService;
    private final CategoryService categoryService;

    @Override
    public CategoryDto populate(CategoryModel source, CategoryDto target) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);

        target.setId(source.getId());
        target.setTitle(localizedStringService.getLocalizedStringValue(source.getTitle()));

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
    public CategoryModel reversePopulate(CategoryDto source, CategoryModel target) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);

        target.setId(source.getId());
        target.setTitle(localizedStringService.addLocalizedStringValue(source.getTitle(), target.getTitle()));

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
