package ee.himaster.platform.services.model.quiz.answer.option;

import ee.himaster.core.localization.model.LocalizedStringModel;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "answer_option_selective_boolean")
public class SelectiveBooleanAnswerOptionModel extends AnswerOptionModel {
    private Boolean value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "title")
    private LocalizedStringModel title;
}
