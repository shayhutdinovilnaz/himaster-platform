package ee.himaster.platform.services.repository;

import ee.himaster.platform.services.model.quiz.QuizItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizItemRepository extends JpaRepository<QuizItemModel, Integer> {
}
