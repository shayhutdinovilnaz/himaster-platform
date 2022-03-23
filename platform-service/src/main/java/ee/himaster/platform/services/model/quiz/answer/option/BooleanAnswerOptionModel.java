package ee.himaster.platform.services.model.quiz.answer.option;

import ee.himaster.core.localization.model.LocalizedStringValueModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity(name = "answer_option_boolean")
public class BooleanAnswerOptionModel extends AnswerOptionModel {
    private Boolean value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "title")
    private LocalizedStringValueModel title;
}
