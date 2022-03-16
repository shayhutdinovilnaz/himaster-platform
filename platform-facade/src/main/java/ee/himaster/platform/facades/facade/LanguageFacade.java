package ee.himaster.platform.facades.facade;

import ee.himaster.platform.dto.LanguageDto;

import java.util.List;

public interface LanguageFacade {
    /**
     * Find languages available for country
     *
     * @param countryIsoCode - country's iso-code
     * @return the languages in the country
     */
    List<LanguageDto> getLanguages(String countryIsoCode);
}
