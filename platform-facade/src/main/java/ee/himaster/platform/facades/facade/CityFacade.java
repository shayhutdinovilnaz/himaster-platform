package ee.himaster.platform.facades.facade;

import ee.himaster.platform.dto.CityDto;

import java.util.List;

public interface CityFacade {
    /**
     * Find cities available for country
     *
     * @param countryIsoCode - country's iso-code
     * @return the cities
     */
    List<CityDto> getCities(String countryIsoCode);
}
