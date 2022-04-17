package ee.himaster.platform.services.model.quiz.answer;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class InputStringAnswerModel extends AnswerModel {

    @Column(name = "varchar_value")
    private String value;
}
