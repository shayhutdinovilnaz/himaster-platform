package ee.himaster.platform.facades.facade;

import ee.himaster.platform.dto.LocaleDto;

public interface LocaleFacade {

    LocaleDto getLocale(String regionCode, String languageIsoCode);
}
