package ee.himaster.platform.services.model.quiz.answer;

import ee.himaster.core.service.model.ItemModel;
import ee.himaster.platform.services.model.quiz.QuizItemModel;
import ee.himaster.platform.services.model.quiz.answer.option.AnswerOptionModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public abstract class AnswerModel extends ItemModel {

    @ManyToOne
    @JoinColumn
    private AnswerOptionModel option;

    @ManyToOne
    @JoinColumn(name = "quiz_item")
    private QuizItemModel quizItem;
}
