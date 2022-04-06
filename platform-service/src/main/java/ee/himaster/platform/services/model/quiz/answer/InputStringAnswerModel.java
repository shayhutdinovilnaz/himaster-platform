package ee.himaster.platform.services.model.quiz.answer;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "answer_input_string")
public class InputStringAnswerModel extends AnswerModel {
    private String value;
}
