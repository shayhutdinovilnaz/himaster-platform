package ee.himaster.platform.services.model.quiz.item;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "quiz_item_string")
public class StringQuizItemModel extends QuizItemModel {
    private String value;
}
