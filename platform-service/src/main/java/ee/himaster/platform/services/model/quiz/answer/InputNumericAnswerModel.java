package ee.himaster.platform.services.model.quiz.answer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "answer_input_numeric")
public class InputNumericAnswerModel extends AnswerModel {
    private Integer value;
}
