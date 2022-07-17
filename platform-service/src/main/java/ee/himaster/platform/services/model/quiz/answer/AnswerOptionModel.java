package ee.himaster.platform.services.model.quiz.answer;

import ee.himaster.core.localization.model.LocalizedStringModel;
import ee.himaster.core.service.model.ItemModel;
import ee.himaster.platform.services.model.quiz.QuestionModel;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "answer_option")
public class AnswerOptionModel extends ItemModel {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "value")
    private LocalizedStringModel value;

    private int priority;

    @ManyToOne
    @JoinColumn(name = "question")
    private QuestionModel question;

    @ManyToOne
    @JoinColumn(name = "next_question")
    private QuestionModel nextQuestion;
}
