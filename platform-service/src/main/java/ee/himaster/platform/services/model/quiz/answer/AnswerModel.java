package ee.himaster.platform.services.model.quiz.answer;

import ee.himaster.core.service.model.ItemModel;
import ee.himaster.platform.services.model.quiz.QuizItemModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "answer")
public class AnswerModel extends ItemModel {

    @ManyToOne
    @JoinColumn(name = "option_id")
    private AnswerOptionModel option;

    @ManyToOne
    @JoinColumn(name = "quiz_item")
    private QuizItemModel quizItem;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerValueModel> values = new ArrayList<>();

}
