package ee.himaster.platform.services.model.quiz;

import ee.himaster.core.service.model.ItemModel;
import ee.himaster.platform.services.model.quiz.answer.AnswerModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "quiz_item")
public class QuizItemModel extends ItemModel {

    private int order;

    @ManyToOne
    @JoinColumn
    private QuestionModel question;

    @OneToMany(mappedBy = "quizItem", cascade = CascadeType.ALL)
    private List<AnswerModel> answers;

    @ManyToOne
    @JoinColumn
    private QuizModel quiz;
}
