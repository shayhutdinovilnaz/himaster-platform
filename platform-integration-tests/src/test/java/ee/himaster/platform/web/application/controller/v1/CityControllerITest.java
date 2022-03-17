package ee.himaster.platform.web.application.controller.v1;

import ee.himaster.core.service.exception.ModelNotFoundException;
import ee.himaster.platform.web.application.controller.AbstractControllerIntegrationTest;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;

public class CityControllerITest extends AbstractControllerIntegrationTest {
    protected static final String QUERY_PARAM_NAME_COUNTRY_ISO_CODE = "countryIsoCode";

    @Test
    public void getAllCitiesInCountry_withoutLocaleCode_exception() throws Exception {
        final var countryIsoCode = "EST";
        final var path = "/v1/city/all";
        final var queryParams = new LinkedMultiValueMap<String, String>();
        queryParams.add(QUERY_PARAM_NAME_COUNTRY_ISO_CODE, countryIsoCode);

        final var headerValues = new LinkedMultiValueMap<String, String>();

        performGetAndMatchResults(path, new HttpHeaders(headerValues), queryParams, HTTP_STATUS_BAD_REQUEST_RESULT_MATCHER);
    }

    @Test
    public void getAllCitiesInCountry_withoutCountryIsoCode_exception() throws Exception {
        final var path = "/v1/city/all";
        final var queryParams = new LinkedMultiValueMap<String, String>();

        final var headerValues = new LinkedMultiValueMap<String, String>();
        headerValues.add(REQUEST_HEADER_LOCALE_CODE_PARAM_NAME, EE_EN_LOCALE_CODE);

        performGetAndMatchResults(path, new HttpHeaders(headerValues), queryParams, HTTP_STATUS_BAD_REQUEST_RESULT_MATCHER);
    }

    @Test
    public void getAllCitiesInCountry_withRuLocaleCode_success() throws Exception {
        final var countryIsoCode = "EST";
        final var path = "/v1/city/all";
        final var queryParams = new LinkedMultiValueMap<String, String>();
        queryParams.add(QUERY_PARAM_NAME_COUNTRY_ISO_CODE, countryIsoCode);

        final var headerValues = new LinkedMultiValueMap<String, String>();
        headerValues.add(REQUEST_HEADER_LOCALE_CODE_PARAM_NAME, EE_RU_LOCALE_CODE);

        performGetAndMatchResults(path, new HttpHeaders(headerValues), queryParams, HTTP_STATUS_OK_RESULT_MATCHER);
    }

    @Test
    public void getAllCitiesInCountry_withEnLocaleCode_success() throws Exception {
        final var countryIsoCode = "EST";
        final var path = "/v1/city/all";
        final var queryParams = new LinkedMultiValueMap<String, String>();
        queryParams.add(QUERY_PARAM_NAME_COUNTRY_ISO_CODE, countryIsoCode);

        final var headerValues = new LinkedMultiValueMap<String, String>();
        headerValues.add(REQUEST_HEADER_LOCALE_CODE_PARAM_NAME, EE_EN_LOCALE_CODE);

        performGetAndMatchResults(path, new HttpHeaders(headerValues), queryParams, HTTP_STATUS_OK_RESULT_MATCHER);
    }

    @Test
    public void getAllCitiesInCountry_countryNotExist_fail() throws Exception {
        final var countryIsoCode = "NON";
        final var path = "/v1/city/all";
        final var queryParams = new LinkedMultiValueMap<String, String>();
        queryParams.add(QUERY_PARAM_NAME_COUNTRY_ISO_CODE, countryIsoCode);

        final var headerValues = new LinkedMultiValueMap<String, String>();
        headerValues.add(REQUEST_HEADER_LOCALE_CODE_PARAM_NAME, EE_EN_LOCALE_CODE);

        performGetAndMatchResults(path, new HttpHeaders(headerValues), queryParams, HTTP_STATUS_BAD_REQUEST_RESULT_MATCHER);
    }
}
