package ee.himaster.platform.services.model.quiz;

import ee.himaster.core.service.model.ItemModel;
import ee.himaster.platform.services.model.CategoryModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity(name = "question_mapping")
public class QuestionMappingModel extends ItemModel {

    @ManyToOne
    @JoinColumn(nullable = false, name = "category_id")
    private CategoryModel category;

    @ManyToOne
    @JoinColumn(nullable = false, name = "start_question")
    private QuestionModel startQuestion;
}
