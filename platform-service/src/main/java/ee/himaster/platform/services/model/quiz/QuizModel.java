package ee.himaster.platform.services.model.quiz;

import ee.himaster.core.service.model.ItemModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity(name = "quiz")
public class QuizModel extends ItemModel {

    private int currentStep;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<QuizItemModel> items;
}