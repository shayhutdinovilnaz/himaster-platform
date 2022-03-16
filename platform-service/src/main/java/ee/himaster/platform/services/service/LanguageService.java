package ee.himaster.platform.services.service;

import ee.himaster.core.service.service.ModelService;
import ee.himaster.platform.services.model.LanguageModel;

import java.util.List;

public interface LanguageService extends ModelService<LanguageModel> {
    List<LanguageModel> getByCountry(String countryIsoCode);
}
