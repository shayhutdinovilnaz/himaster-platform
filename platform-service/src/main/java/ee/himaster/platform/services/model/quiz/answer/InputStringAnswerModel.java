package ee.himaster.platform.services.model.quiz.answer;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("INPUT_STRING")
public class InputStringAnswerModel extends AnswerModel {

    @Column(name = "varchar_value")
    private String value;
}
