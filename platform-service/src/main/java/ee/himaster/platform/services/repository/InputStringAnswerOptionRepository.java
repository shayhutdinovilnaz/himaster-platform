package ee.himaster.platform.services.repository;

import ee.himaster.platform.services.model.quiz.answer.option.InputStringAnswerOptionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputStringAnswerOptionRepository extends JpaRepository<InputStringAnswerOptionModel, Integer> {
}
