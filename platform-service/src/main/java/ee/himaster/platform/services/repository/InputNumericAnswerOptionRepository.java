package ee.himaster.platform.services.repository;

import ee.himaster.platform.services.model.quiz.answer.option.InputNumericAnswerOptionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputNumericAnswerOptionRepository extends JpaRepository<InputNumericAnswerOptionModel, Integer> {
}
