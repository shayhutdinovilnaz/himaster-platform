package ee.himaster.platform.services.repository;

import ee.himaster.platform.services.model.FacetModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacetRepository extends JpaRepository<FacetModel, Long> {
    FacetModel getByCode(String code);
}
