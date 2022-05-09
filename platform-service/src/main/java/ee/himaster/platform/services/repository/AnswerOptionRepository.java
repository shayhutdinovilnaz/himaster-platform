package ee.himaster.platform.services.repository;

import ee.himaster.platform.services.model.quiz.answer.AnswerOptionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerOptionRepository extends JpaRepository<AnswerOptionModel, Integer> {
}
