package ee.himaster.categories.model;

import ee.himaster.core.service.model.ItemModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class CategoryModel extends ItemModel {
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn
    private CategoryModel parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private Set<CategoryModel> childrenCategories;
}