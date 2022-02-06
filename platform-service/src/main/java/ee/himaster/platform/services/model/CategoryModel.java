package ee.himaster.platform.services.model;

import ee.himaster.core.localization.model.LocalizedStringModel;
import ee.himaster.core.service.model.ItemModel;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString(exclude = {"parentCategory"})
public class CategoryModel extends ItemModel {

    @ManyToOne
    @JoinColumn
    private LocalizedStringModel name;

    @ManyToOne
    @JoinColumn
    private CategoryModel parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private List<CategoryModel> childrenCategories;
}