package ee.himaster.platform.services.repository;

import ee.himaster.platform.services.model.quiz.QuizModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<QuizModel, Integer> {
    QuizModel getByUserId(Integer userId);

    QuizModel getBySessionId(String sessionId);
}
