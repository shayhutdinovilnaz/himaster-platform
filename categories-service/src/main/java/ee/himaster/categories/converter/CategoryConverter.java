package ee.himaster.categories.converter;

import ee.himaster.categories.dto.CategoryDto;
import ee.himaster.categories.model.CategoryModel;
import ee.himaster.core.service.converter.impl.BasicConverter;
import ee.himaster.core.service.populator.Populator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter extends BasicConverter<CategoryDto, CategoryModel> {
    public CategoryConverter(List<Populator<CategoryDto, CategoryModel>> populators,
            Supplier<CategoryDto> categoryDtoSupplier,
            Supplier<CategoryModel> categoryModelSupplier) {
        super(populators, categoryDtoSupplier, categoryModelSupplier);
    }

    @Override
    public CategoryDto convert(CategoryModel source) {
        final CategoryDto target = super.convert(source);
        if (CollectionUtils.isNotEmpty(source.getChildrenCategories())) {
            final List<CategoryDto> childrenCategories = source.getChildrenCategories()
                    .stream()
                    .map(this::convert)
                    .collect(Collectors.toList());
            target.setChildren(childrenCategories);
        }

        if (source.getParentCategory() != null) {
            target.setParent(convert(source));
        }

        return target;
    }

    @Override
    public CategoryModel reverseConvert(CategoryDto source) {
        final CategoryModel target = super.reverseConvert(source);

        if (CollectionUtils.isNotEmpty(source.getChildren())) {
            final Set<CategoryModel> childrenCategories = source.getChildren()
                    .stream()
                    .map(this::reverseConvert)
                    .collect(Collectors.toSet());
            target.setChildrenCategories(childrenCategories);
        }

        if (source.getParent() != null) {
            target.setParentCategory(reverseConvert(source));
        }

        return target;
    }
}
