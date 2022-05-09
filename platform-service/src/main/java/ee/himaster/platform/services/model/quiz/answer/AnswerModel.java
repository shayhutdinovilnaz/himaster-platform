package ee.himaster.platform.services.model.quiz.answer;

import ee.himaster.core.service.model.ItemModel;
import ee.himaster.platform.services.model.quiz.QuizItemModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "answer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="answer_type",
        discriminatorType = DiscriminatorType.STRING)
public abstract class AnswerModel extends ItemModel {

    @ManyToOne
    @JoinColumn(name = "option_id")
    private AnswerOptionModel option;

    @ManyToOne
    @JoinColumn(name = "quiz_item")
    private QuizItemModel quizItem;
}
