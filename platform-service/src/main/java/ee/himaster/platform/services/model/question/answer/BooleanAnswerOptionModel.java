package ee.himaster.platform.services.model.question.answer;

import ee.himaster.core.localization.model.LocalizedStringValueModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity(name = "boolean_answer_option")
public class BooleanAnswerOptionModel extends AnswerOptionModel {
    private Boolean value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "localized_value")
    private LocalizedStringValueModel localizedValue;
}
