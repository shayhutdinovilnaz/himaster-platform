package ee.himaster.platform.services.model.quiz.item;

import ee.himaster.core.service.model.ItemModel;
import ee.himaster.platform.services.model.quiz.QuizModel;
import ee.himaster.platform.services.model.quiz.answer.AnswerOptionModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "quiz_item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class QuizItemModel extends ItemModel {

    private int order;

    private String qualifier;

    @ManyToOne
    @JoinColumn(name = "answer_option")
    private AnswerOptionModel answerOption;

    @ManyToOne
    @JoinColumn(name = "quiz")
    private QuizModel quiz;
}
