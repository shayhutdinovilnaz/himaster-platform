package ee.himaster.platform.services.repository;

import ee.himaster.platform.services.model.quiz.answer.option.SelectiveBooleanAnswerOptionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectiveBooleanAnswerOptionRepository extends JpaRepository<SelectiveBooleanAnswerOptionModel, Integer> {
}
