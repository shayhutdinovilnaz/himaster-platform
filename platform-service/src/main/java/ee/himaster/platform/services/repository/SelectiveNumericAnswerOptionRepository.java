package ee.himaster.platform.services.repository;

import ee.himaster.platform.services.model.quiz.answer.option.SelectiveNumericAnswerOptionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectiveNumericAnswerOptionRepository extends JpaRepository<SelectiveNumericAnswerOptionModel, Integer> {
}
