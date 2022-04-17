package ee.himaster.platform.services.model.quiz.answer.option;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class SelectiveNumericAnswerOptionModel extends AnswerOptionModel {

    @Column(name = "integer_value")
    private Integer value;
}
