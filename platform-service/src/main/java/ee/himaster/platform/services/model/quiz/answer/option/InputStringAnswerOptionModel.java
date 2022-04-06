package ee.himaster.platform.services.model.quiz.answer.option;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "answer_option_input_string")
public class InputStringAnswerOptionModel extends AnswerOptionModel {
}
