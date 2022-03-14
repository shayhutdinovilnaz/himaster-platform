package ee.himaster.platform.facades.facade.impl;

import ee.himaster.core.localization.model.Country;
import ee.himaster.core.service.converter.impl.BasicConverter;
import ee.himaster.platform.dto.CityDto;
import ee.himaster.platform.facades.facade.CityFacade;
import ee.himaster.platform.services.model.CityModel;
import ee.himaster.platform.services.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultCityFacade implements CityFacade {
    private final CityService categoryService;
    private final BasicConverter<CityDto, CityModel> cityConverter;

    @Override
    public List<CityDto> getCities(final String countryIsoCode) {
        return null;
    }
}
