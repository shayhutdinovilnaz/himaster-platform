package ee.himaster.platform.services.model.quiz;

import ee.himaster.core.service.model.ItemModel;
import ee.himaster.platform.services.model.CategoryModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "quiz")
public class QuizModel extends ItemModel {

    @Column(name = "current_step")
    private int currentStep;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "session_id")
    private Integer sessionId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CategoryModel category;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<QuizItemModel> items = new ArrayList<>();
}
