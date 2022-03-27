package ee.himaster.platform.services.model.quiz;

import ee.himaster.core.service.model.ItemModel;
import ee.himaster.platform.services.model.quiz.answer.AnswerModel;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "quiz_item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizItemModel extends ItemModel {

    private int order;

    @ManyToOne
    @JoinColumn
    private QuestionModel question;

    @OneToMany(mappedBy = "quizItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerModel> answers = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private QuizModel quiz;
}
