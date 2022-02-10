package ee.himaster.platform.services.model;

import ee.himaster.core.localization.model.LocalizedStringModel;
import ee.himaster.core.service.model.ItemModel;

import java.util.List;
import javax.persistence.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static ee.himaster.platform.services.model.CategoryModel.ENTITY_GRAPH_PARAMETERS_WITH_CHILDREN_CATEGORIES;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString(exclude = {"parentCategory", "childrenCategories"})
@NamedEntityGraph(name = ENTITY_GRAPH_PARAMETERS_WITH_CHILDREN_CATEGORIES, attributeNodes = @NamedAttributeNode(value = "childrenCategories"))
public class CategoryModel extends ItemModel {
    public static final String ENTITY_GRAPH_PARAMETERS_WITH_CHILDREN_CATEGORIES = "withChildrenCategories";

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private LocalizedStringModel name;

    @ManyToOne
    @JoinColumn
    private CategoryModel parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<CategoryModel> childrenCategories;
}