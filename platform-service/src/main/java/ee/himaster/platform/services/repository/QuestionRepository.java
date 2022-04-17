package ee.himaster.platform.services.repository;

import ee.himaster.platform.services.model.CategoryModel;
import ee.himaster.platform.services.model.quiz.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<QuestionModel, Integer> {

    @Query("SELECT u FROM question u WHERE u.id=1")
    QuestionModel findInitializeQuestion(CategoryModel category);

    @Query("SELECT u FROM question u WHERE u.id=1")
    QuestionModel findSummarizeQuestion(CategoryModel category);
}
