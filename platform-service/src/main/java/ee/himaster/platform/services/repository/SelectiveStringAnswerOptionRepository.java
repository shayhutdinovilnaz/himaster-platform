package ee.himaster.platform.services.repository;

import ee.himaster.platform.services.model.quiz.answer.option.SelectiveStringAnswerOptionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectiveStringAnswerOptionRepository extends JpaRepository<SelectiveStringAnswerOptionModel, Integer> {
}
