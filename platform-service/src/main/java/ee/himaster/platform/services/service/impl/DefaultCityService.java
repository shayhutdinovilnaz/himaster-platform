package ee.himaster.platform.services.service.impl;

import ee.himaster.core.service.exception.ModelNotFoundException;
import ee.himaster.core.service.service.impl.AbstractModelService;
import ee.himaster.platform.services.model.CityModel;
import ee.himaster.platform.services.model.CountryModel;
import ee.himaster.platform.services.repository.CityRepository;
import ee.himaster.platform.services.repository.CountryRepository;
import ee.himaster.platform.services.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultCityService extends AbstractModelService<CityModel> implements CityService {
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    @Override
    @Transactional
    public List<CityModel> getByCountry(final String countryIsoCode) {
        return Optional.ofNullable(countryRepository.getByIsoCode(countryIsoCode))
                .map(CountryModel::getCities)
                .orElseThrow(() -> new ModelNotFoundException("Country is not found. Country code: " + countryIsoCode));
    }

    @Override
    protected JpaRepository<CityModel, Integer> getItemRepository() {
        return cityRepository;
    }
}
