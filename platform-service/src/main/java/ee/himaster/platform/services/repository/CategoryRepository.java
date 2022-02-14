package ee.himaster.platform.services.repository;

import ee.himaster.platform.services.model.CategoryModel;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            value = CategoryModel.ENTITY_GRAPH_PARAMETERS_WITH_CHILDREN_CATEGORIES)
    List<CategoryModel> getAllByParentCategoryIsNull();

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            value = CategoryModel.ENTITY_GRAPH_PARAMETERS_WITH_CHILDREN_CATEGORIES)
    Optional<CategoryModel> findById(Integer id);
}
