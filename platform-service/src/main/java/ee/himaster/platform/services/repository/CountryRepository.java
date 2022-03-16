package ee.himaster.platform.services.repository;

import ee.himaster.platform.services.model.CountryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryModel, Integer> {
    CountryModel getByIsoCode(String isoCode);
}
