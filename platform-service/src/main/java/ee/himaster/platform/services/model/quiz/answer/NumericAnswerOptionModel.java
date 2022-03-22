package ee.himaster.platform.services.model.quiz.answer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "answer_option_numeric")
public class NumericAnswerOptionModel extends AnswerOptionModel {
    private Integer value;
}
