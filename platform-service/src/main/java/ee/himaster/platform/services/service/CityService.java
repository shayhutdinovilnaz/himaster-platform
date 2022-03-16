package ee.himaster.platform.services.service;

import ee.himaster.core.service.service.ModelService;
import ee.himaster.platform.services.model.CityModel;

import java.util.List;

public interface CityService extends ModelService<CityModel> {
    List<CityModel> getByCountry(String countryIsoCode);
}
