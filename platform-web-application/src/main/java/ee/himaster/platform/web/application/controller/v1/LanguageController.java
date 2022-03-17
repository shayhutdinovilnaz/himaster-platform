package ee.himaster.platform.web.application.controller.v1;

import ee.himaster.platform.api.controller.LanguageApi;
import ee.himaster.platform.dto.LanguageDto;
import ee.himaster.platform.facades.facade.LanguageFacade;
import ee.himaster.platform.web.application.controller.BaseController;
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
public class LanguageController extends BaseController implements LanguageApi {
    private final LanguageFacade languageFacade;

    @Override
    public ResponseEntity<List<LanguageDto>> getAllLanguagesInCountry(String countryIsoCode, String localeCode) {
        return ResponseEntity.ok(languageFacade.getLanguages(countryIsoCode));
    }
}
