package ee.himaster.platform.web.application.controller.v1;

import ee.himaster.platform.api.controller.SiteconfigApi;
import ee.himaster.platform.dto.CityDto;
import ee.himaster.platform.dto.LanguageDto;
import ee.himaster.platform.dto.LocaleDto;
import ee.himaster.platform.facades.facade.CityFacade;
import ee.himaster.platform.facades.facade.LanguageFacade;
import ee.himaster.platform.facades.facade.LocaleFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1")
@RestController
@Slf4j
public class SiteConfigController implements SiteconfigApi {
    private final CityFacade cityFacade;
    private final LanguageFacade languageFacade;
    private final LocaleFacade localeFacade;

    @Override
    public ResponseEntity<List<CityDto>> getAllCities(String localeCode) {
        return ResponseEntity.ok(cityFacade.getCities());
    }

    @Override
    public ResponseEntity<List<LanguageDto>> getAllLanguages(String localeCode) {
        return ResponseEntity.ok(languageFacade.getLanguages());
    }

    @Override
    public ResponseEntity<LocaleDto> getLocaleByRegionAndLanguage(String regionCode, String languageIsoCode) {
        return ResponseEntity.ok(localeFacade.getLocale(regionCode, languageIsoCode));
    }
}
