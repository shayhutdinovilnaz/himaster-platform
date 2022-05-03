package ee.himaster.platform.services.model.quiz.answer.option;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("INPUT_STRING")
public class InputStringAnswerOptionModel extends AnswerOptionModel {
}
