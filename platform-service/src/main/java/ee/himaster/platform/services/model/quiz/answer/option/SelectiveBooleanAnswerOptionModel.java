package ee.himaster.platform.services.model.quiz.answer.option;

import ee.himaster.core.localization.model.LocalizedStringModel;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SelectiveBooleanAnswerOptionModel extends AnswerOptionModel {

    @Column(name = "boolean_value")
    private Boolean value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "title")
    private LocalizedStringModel title;
}
