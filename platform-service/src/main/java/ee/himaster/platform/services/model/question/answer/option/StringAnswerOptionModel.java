package ee.himaster.platform.services.model.question.answer.option;

import ee.himaster.core.localization.model.LocalizedStringValueModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity(name = "answer_option_string")
public class StringAnswerOptionModel extends AnswerOptionModel {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "value")
    private LocalizedStringValueModel value;
}