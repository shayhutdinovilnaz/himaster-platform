package ee.himaster.platform.services.model.quiz.answer.option;

import ee.himaster.core.localization.model.LocalizedStringModel;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("SELECTIVE_STRING")
public class SelectiveStringAnswerOptionModel extends AnswerOptionModel {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "local_string_value")
    private LocalizedStringModel value;
}
