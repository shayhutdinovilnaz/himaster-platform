package ee.himaster.platform.services.model;

import ee.himaster.core.localization.model.LocalizedStringModel;
import ee.himaster.core.service.model.ItemModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static ee.himaster.platform.services.model.CategoryModel.ENTITY_GRAPH_PARAMETERS_WITH_CHILDREN_CATEGORIES;

@Entity(name = "category")
@Getter
@Setter
@RequiredArgsConstructor
@NamedEntityGraph(name = ENTITY_GRAPH_PARAMETERS_WITH_CHILDREN_CATEGORIES, attributeNodes = @NamedAttributeNode(value = "childrenCategories"))
public class CategoryModel extends ItemModel {
    public static final String ENTITY_GRAPH_PARAMETERS_WITH_CHILDREN_CATEGORIES = "withChildrenCategories";

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "title")
    private LocalizedStringModel title;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private CategoryModel parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<CategoryModel> childrenCategories;
}