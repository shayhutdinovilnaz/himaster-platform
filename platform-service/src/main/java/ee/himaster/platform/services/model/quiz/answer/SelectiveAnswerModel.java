package ee.himaster.platform.services.model.quiz.answer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("SELECTIVE")
public class SelectiveAnswerModel extends AnswerModel {
}
