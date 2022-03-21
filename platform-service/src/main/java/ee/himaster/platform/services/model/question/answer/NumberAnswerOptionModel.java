package ee.himaster.platform.services.model.question.answer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "number_answer_option")
public class NumberAnswerOptionModel extends AnswerOptionModel {
    private Integer value;
}
