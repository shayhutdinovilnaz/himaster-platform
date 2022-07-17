package ee.himaster.platform.services.model.quiz.answer;

import ee.himaster.core.service.model.ItemModel;
import ee.himaster.platform.services.model.FacetModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity(name = "answer_value")
public class AnswerValueModel extends ItemModel {

    @ManyToOne
    @JoinColumn(name = "facet")
    private FacetModel facet;

    private String value;

    @ManyToOne
    @JoinColumn(name = "answer")
    private AnswerModel answer;
}
