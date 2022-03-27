package ee.himaster.platform.services.model.quiz;

import ee.himaster.core.service.model.ItemModel;
import ee.himaster.platform.services.model.CategoryModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity(name = "question_mapping")
public class QuestionMappingModel extends ItemModel {

    @ManyToOne
    @JoinColumn(nullable = false)
    private CategoryModel category;

    @ManyToOne
    @JoinColumn(nullable = false)
    private QuestionModel question;

    @Column(name = "initial_question")
    private boolean initialQuestion;

    @Column(name = "summarize_question")
    private boolean summarizeQuestion;
}
