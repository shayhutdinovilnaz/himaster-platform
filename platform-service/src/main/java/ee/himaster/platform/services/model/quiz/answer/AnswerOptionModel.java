package ee.himaster.platform.services.model.quiz.answer;

import ee.himaster.core.localization.model.LocalizedStringValueModel;
import ee.himaster.core.service.model.ItemModel;
import ee.himaster.platform.services.model.quiz.QuestionModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "answer_option")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AnswerOptionModel extends ItemModel {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "label")
    private LocalizedStringValueModel label;

    private String qualifier;

    private int priority;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question")
    private QuestionModel question;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "next_question")
    private QuestionModel nextQuestion;
}
