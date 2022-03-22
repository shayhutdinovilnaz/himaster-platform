package ee.himaster.platform.services.model.question;

import ee.himaster.core.localization.model.LocalizedStringValueModel;
import ee.himaster.core.service.model.ItemModel;
import ee.himaster.platform.services.model.question.answer.option.AnswerOptionModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "question")
public class QuestionModel extends ItemModel {

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    private String qualifier;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "title")
    private LocalizedStringValueModel title;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<AnswerOptionModel> answerOptions;

}
