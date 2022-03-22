package ee.himaster.platform.services.model.question.answer.option;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "answer_option_number")
public class NumberAnswerOptionModel extends AnswerOptionModel {
    private Integer value;
}
