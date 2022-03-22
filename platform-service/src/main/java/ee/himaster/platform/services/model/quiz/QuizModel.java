package ee.himaster.platform.services.model.quiz;

import ee.himaster.core.service.model.ItemModel;
import ee.himaster.platform.services.model.quiz.item.QuizItemModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "quiz")
public class QuizModel extends ItemModel {

    private int step;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<QuizItemModel> items;
}
