package ee.himaster.platform.services.repository;

import ee.himaster.platform.services.model.CityModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityModel, Integer> {
}
