package ee.himaster.platform.services.repository;

import ee.himaster.platform.services.model.CategoryModel;
import ee.himaster.platform.services.model.quiz.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionModel, Integer> {

    QuestionModel findInitializeQuestion(CategoryModel category);

    QuestionModel findSummarizeQuestion(CategoryModel category);
}
