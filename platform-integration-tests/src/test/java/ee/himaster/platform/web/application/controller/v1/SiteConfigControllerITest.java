package ee.himaster.platform.web.application.controller.v1;

import ee.himaster.core.localization.model.Language;
import ee.himaster.core.localization.model.Region;
import ee.himaster.platform.dto.CityDto;
import ee.himaster.platform.dto.LanguageDto;
import ee.himaster.platform.dto.LocaleDto;
import ee.himaster.platform.web.application.controller.AbstractControllerIntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

public class SiteConfigControllerITest extends AbstractControllerIntegrationTest {

    @Test
    public void getAllCitiesInCountry_withoutLocaleCode_exception() throws Exception {
        final var path = "/v1/siteconfig/city/all";

        final var headerValues = new LinkedMultiValueMap<String, String>();

        performGetAndMatchResults(path, new HttpHeaders(headerValues), HTTP_STATUS_BAD_REQUEST_RESULT_MATCHER);
    }

    @Test
    public void getAllCitiesInCountry_withRuLocaleCode_success() throws Exception {
        final var path = "/v1/siteconfig/city/all";

        final var headerValues = new LinkedMultiValueMap<String, String>();
        headerValues.add(REQUEST_HEADER_LOCALE_CODE_PARAM_NAME, EE_RU_LOCALE_CODE);

        var response =
                performGetAndMatchResults(path, new HttpHeaders(headerValues), HTTP_STATUS_OK_RESULT_MATCHER);
        List<CityDto> cities = getObjectFromJsonBody(response, List.class);

        Assert.assertEquals(3, cities.size());
    }

    @Test
    public void getAllCitiesInCountry_withEnLocaleCode_success() throws Exception {
        final var path = "/v1/siteconfig/city/all";

        final var headerValues = new LinkedMultiValueMap<String, String>();
        headerValues.add(REQUEST_HEADER_LOCALE_CODE_PARAM_NAME, EE_EN_LOCALE_CODE);

        var response =
                performGetAndMatchResults(path, new HttpHeaders(headerValues), HTTP_STATUS_OK_RESULT_MATCHER);
        List<CityDto> cities = getObjectFromJsonBody(response, List.class);

        Assert.assertEquals(3, cities.size());
    }

    @Test
    public void getAllLanguagesInCountry_withoutLocaleCode_exception() throws Exception {
        final var path = "/v1/siteconfig/language/all";

        final var headerValues = new LinkedMultiValueMap<String, String>();

        performGetAndMatchResults(path, new HttpHeaders(headerValues), HTTP_STATUS_BAD_REQUEST_RESULT_MATCHER);
    }

    @Test
    public void getAllLanguagesInCountry_withRuLocaleCode_success() throws Exception {
        final var path = "/v1/siteconfig/language/all";

        final var headerValues = new LinkedMultiValueMap<String, String>();
        headerValues.add(REQUEST_HEADER_LOCALE_CODE_PARAM_NAME, EE_RU_LOCALE_CODE);

        var response =
                performGetAndMatchResults(path, new HttpHeaders(headerValues), HTTP_STATUS_OK_RESULT_MATCHER);
        List<LanguageDto> languages = getObjectFromJsonBody(response, List.class);

        Assert.assertEquals(3, languages.size());
    }

    @Test
    public void getAllLanguagesInCountry_withEnLocaleCode_success() throws Exception {
        final var path = "/v1/siteconfig/language/all";

        final var headerValues = new LinkedMultiValueMap<String, String>();
        headerValues.add(REQUEST_HEADER_LOCALE_CODE_PARAM_NAME, EE_EN_LOCALE_CODE);

        var response =
                performGetAndMatchResults(path, new HttpHeaders(headerValues), HTTP_STATUS_OK_RESULT_MATCHER);
        List<LanguageDto> languages = getObjectFromJsonBody(response, List.class);

        Assert.assertEquals(3, languages.size());
    }

    @Test
    public void getLocaleByRegionAndLanguage_withoutRegionCode_exception() throws Exception {
        final var path = "/v1/siteconfig/locale";
        final var queryParams = new LinkedMultiValueMap<String, String>();
        final var headerValues = new LinkedMultiValueMap<String, String>();

        performGetAndMatchResults(path, new HttpHeaders(headerValues), queryParams, HTTP_STATUS_BAD_REQUEST_RESULT_MATCHER);
    }

    @Test
    public void getLocaleByRegionAndLanguage_withEstRegion_success() throws Exception {
        final var path = "/v1/siteconfig/locale";
        final var headerValues = new LinkedMultiValueMap<String, String>();
        final var queryParams = new LinkedMultiValueMap<String, String>();
        queryParams.add("regionCode", Region.ESTONIA_GENERAL.getCode());

        var response =
                performGetAndMatchResults(path, new HttpHeaders(headerValues), queryParams, HTTP_STATUS_OK_RESULT_MATCHER);
        var localeDto = getObjectFromJsonBody(response, LocaleDto.class);

        Assert.assertEquals("EST_EE", localeDto.getCode());
        Assert.assertEquals(Language.ESTONIAN.getIsoCode(), localeDto.getLanguageIsoCode());
    }

    @Test
    public void getLocaleByRegionAndLanguage_withEstRegionAndLanguage_success() throws Exception {
        final var path = "/v1/siteconfig/locale";
        final var headerValues = new LinkedMultiValueMap<String, String>();
        final var queryParams = new LinkedMultiValueMap<String, String>();
        queryParams.add("regionCode", Region.ESTONIA_GENERAL.getCode());
        queryParams.add("languageIsoCode", Language.RUSSIAN.getIsoCode());

        var response =
                performGetAndMatchResults(path, new HttpHeaders(headerValues), queryParams, HTTP_STATUS_OK_RESULT_MATCHER);
        var localeDto = getObjectFromJsonBody(response, LocaleDto.class);

        Assert.assertEquals("EST_RU", localeDto.getCode());
        Assert.assertEquals(Language.RUSSIAN.getIsoCode(), localeDto.getLanguageIsoCode());
    }

    @Test
    public void getAllCitiesInCountry_withNotExistRegionCode_fail() throws Exception {
        final var countryIsoCode = "NOT FOUND";
        final var path = "/v1/siteconfig/locale";
        final var headerValues = new LinkedMultiValueMap<String, String>();
        final var queryParams = new LinkedMultiValueMap<String, String>();
        queryParams.add("regionCode", countryIsoCode);
        queryParams.add("languageIsoCode", Language.RUSSIAN.getIsoCode());

        performGetAndMatchResults(path, new HttpHeaders(headerValues), queryParams, HTTP_STATUS_BAD_REQUEST_RESULT_MATCHER);
    }

    @Test
    public void getAllCitiesInCountry_withNotExistLanguageIsoCode_fail() throws Exception {
        final var languageIsoCode = "NOT FOUND";
        final var path = "/v1/siteconfig/locale";
        final var headerValues = new LinkedMultiValueMap<String, String>();
        final var queryParams = new LinkedMultiValueMap<String, String>();
        queryParams.add("regionCode", Region.ESTONIA_GENERAL.getCode());
        queryParams.add("languageIsoCode", languageIsoCode);

        performGetAndMatchResults(path, new HttpHeaders(headerValues), queryParams, HTTP_STATUS_BAD_REQUEST_RESULT_MATCHER);
    }


}
