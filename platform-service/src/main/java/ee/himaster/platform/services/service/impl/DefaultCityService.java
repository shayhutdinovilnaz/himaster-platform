package ee.himaster.platform.services.service.impl;

import ee.himaster.core.service.service.impl.AbstractModelService;
import ee.himaster.platform.services.model.CityModel;
import ee.himaster.platform.services.repository.CityRepository;
import ee.himaster.platform.services.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultCityService extends AbstractModelService<CityModel> implements CityService {
    private final CityRepository cityRepository;

    @Override
    protected JpaRepository<CityModel, Integer> getItemRepository() {
        return cityRepository;
    }
}
