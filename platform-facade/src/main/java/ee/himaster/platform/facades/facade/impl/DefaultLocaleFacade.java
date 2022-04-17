package ee.himaster.platform.facades.facade.impl;

import ee.himaster.core.localization.model.Language;
import ee.himaster.core.localization.model.LocaleModel;
import ee.himaster.core.localization.model.Region;
import ee.himaster.core.localization.service.LocaleService;
import ee.himaster.core.service.converter.impl.BasicConverter;
import ee.himaster.core.service.exception.ModelNotFoundException;
import ee.himaster.platform.dto.LocaleDto;
import ee.himaster.platform.facades.converter.LocaleConverter;
import ee.himaster.platform.facades.facade.LocaleFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultLocaleFacade implements LocaleFacade {
    private final LocaleService localeService;
    private final LocaleConverter localeConverter;

    @Override
    public LocaleDto getLocale(final String regionCode, final String languageIsoCode) {
        try {
            final var region = Region.get(regionCode);
            Language language = null;

            if (languageIsoCode != null) {
                language = Language.get(languageIsoCode);
            }

            return localeConverter.convert(localeService.getLocale(region, language));
        } catch (IllegalArgumentException ie) {
            log.error("Region or language was not defined. Region's code: {}, Language's code: {}.", regionCode, languageIsoCode, ie);
            throw new ModelNotFoundException("Region or language was not defined. Region's code: " + regionCode + ", language's code: " + languageIsoCode);
        }
    }
}
