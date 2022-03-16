package ee.himaster.platform.services.repository;

import ee.himaster.platform.services.model.LanguageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<LanguageModel, Integer> {
}
