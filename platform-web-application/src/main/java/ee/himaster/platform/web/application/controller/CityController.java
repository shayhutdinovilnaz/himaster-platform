package ee.himaster.platform.web.application.controller;

import ee.himaster.platform.api.controller.CityApi;
import ee.himaster.platform.dto.CityDto;
import ee.himaster.platform.facades.facade.CityFacade;
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
public class CityController implements CityApi {
    private final CityFacade cityFacade;

    @Override
    public ResponseEntity<List<CityDto>> getAllCitiesInCountry(String countryIsoCode, String localeCode) {
        return ResponseEntity.ok(cityFacade.getCities(countryIsoCode));
    }
}