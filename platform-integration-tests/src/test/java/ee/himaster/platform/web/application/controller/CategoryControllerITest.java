package ee.himaster.platform.web.application.controller;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;

public class CategoryControllerITest extends AbstractControllerIntegrationTest {
    @Test
    public void getById_withoutLocaleCode_exception() throws Exception {
        final var id = 1;
        final var path = "/v1/category/" + id;
        performGetAndMatchResults(path, HTTP_STATUS_BAD_REQUEST_RESULT_MATCHER);
    }

    @Test
    public void getById_withRuLocaleCode_success() throws Exception {
        final var id = 1;
        final var path = "/v1/category/" + id;
        final var headerValues = new LinkedMultiValueMap<String, String>();
        headerValues.add(REQUEST_HEADER_LOCALE_CODE_PARAM_NAME, EE_RU_LOCALE_CODE);
        performGetAndMatchResults(path, new HttpHeaders(headerValues), HTTP_STATUS_OK_RESULT_MATCHER);
    }

    @Test
    public void getById_withEnLocaleCode_success() throws Exception {
        final var id = 1;
        final var path = "/v1/category/" + id;
        final var headerValues = new LinkedMultiValueMap<String, String>();
        headerValues.add(REQUEST_HEADER_LOCALE_CODE_PARAM_NAME, EE_EN_LOCALE_CODE);
        performGetAndMatchResults(path, new HttpHeaders(headerValues), HTTP_STATUS_OK_RESULT_MATCHER);
    }

    @Test
    public void getById_categoryNotExist_fail() {
//        final var id = 1;
//        final var path = "/platform/api/v1/category/" + id;
//        final var headerValues = new LinkedMultiValueMap<String, String>();
//        headerValues.add(REQUEST_HEADER_LOCALE_CODE_PARAM_NAME, EE_EN_LOCALE_CODE);
//        performGetAndMatchResults(path, new HttpHeaders(headerValues), HTTP_STATUS_OK_RESULT_MATCHER);
    }
}
