package ee.himaster.platform.services.model.quiz.answer.option;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "answer_option_selective_numeric")
public class SelectiveNumericAnswerOptionModel extends AnswerOptionModel {
    private Integer value;
}
