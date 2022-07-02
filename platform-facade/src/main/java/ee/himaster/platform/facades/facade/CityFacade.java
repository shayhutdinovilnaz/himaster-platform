package ee.himaster.platform.facades.facade;

import ee.himaster.platform.dto.CityDto;

import java.util.List;

public interface CityFacade {
    /**
     * Find cities available for country
     *
     * @return the cities
     */
    List<CityDto> getCities();
}
