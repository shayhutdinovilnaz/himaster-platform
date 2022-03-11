package ee.himaster.platform.web.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ee.himaster.platform.util.AbstractIntegrationTest;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletResponse;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;

public abstract class AbstractControllerIntegrationTest extends AbstractIntegrationTest {

    protected static final String EE_RU_LOCALE_CODE = "EE_RU";
    protected static final String EE_EN_LOCALE_CODE = "EE_EN";
    protected static final String REQUEST_HEADER_LOCALE_CODE_PARAM_NAME = "Locale-code";

    private ObjectMapper objectMapper;

    protected static final ResultMatcher HTTP_STATUS_CREATED_RESULT_MATCHER = MockMvcResultMatchers.status().isCreated();
    protected static final ResultMatcher HTTP_STATUS_NO_CONTENT_RESULT_MATCHER = MockMvcResultMatchers.status().isNoContent();
    protected static final ResultMatcher HTTP_STATUS_UNPROCESSABLE_ENTITY_RESULT_MATCHER = MockMvcResultMatchers.status().isUnprocessableEntity();
    protected static final ResultMatcher HTTP_STATUS_BAD_REQUEST_RESULT_MATCHER = MockMvcResultMatchers.status().isBadRequest();
    protected static final ResultMatcher HTTP_STATUS_SERVER_ERROR_RESULT_MATCHER = MockMvcResultMatchers.status().is5xxServerError();
    protected static final ResultMatcher HTTP_STATUS_OK_RESULT_MATCHER = MockMvcResultMatchers.status().isOk();
    protected static final ResultMatcher HTTP_STATUS_NOT_FOUND_RESULT_MATCHER = MockMvcResultMatchers.status().isNotFound();

    @Autowired
    protected MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        createObjectMapper();
    }

    protected String asJson(Object requestDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(requestDto);
    }

    protected HttpServletResponse performPostAndMatchResults(String url, String jsonBody, ResultMatcher... resultMatchers) throws Exception {

        HttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders
                                .post(url)
                                .content(jsonBody)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(ResultMatcher.matchAll(resultMatchers))
                        .andReturn().getResponse();

        Assertions.assertThat(response).isNotNull();
        return response;
    }

    protected HttpServletResponse performPostAndMatchResults(String url, String jsonBody, HttpHeaders headers, ResultMatcher... resultMatchers) throws Exception {

        HttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders
                                .post(url)
                                .content(jsonBody)
                                .headers(headers)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(ResultMatcher.matchAll(resultMatchers))
                        .andReturn().getResponse();

        Assertions.assertThat(response).isNotNull();
        return response;
    }

    protected HttpServletResponse performGetAndMatchResults(String url, ResultMatcher... resultMatchers) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                        .get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(ResultMatcher.matchAll(resultMatchers))
                .andReturn().getResponse();
    }

    protected HttpServletResponse performGetAndMatchResults(String url, HttpHeaders headers, ResultMatcher... resultMatchers) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                        .get(url)
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(ResultMatcher.matchAll(resultMatchers))
                .andReturn().getResponse();
    }

    protected HttpServletResponse performDeleteAndMatchResults(String url, ResultMatcher... resultMatchers) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                        .delete(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(ResultMatcher.matchAll(resultMatchers))
                .andReturn().getResponse();
    }

    protected HttpServletResponse performPutAndMatchResults(String url, String jsonBody, ResultMatcher... resultMatchers) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                        .put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(ResultMatcher.matchAll(resultMatchers))
                .andReturn().getResponse();
    }

    protected HttpServletResponse performPutAndMatchResults(String url, String jsonBody, HttpHeaders headers, ResultMatcher... resultMatchers) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                        .put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headers)
                        .content(jsonBody))
                .andExpect(ResultMatcher.matchAll(resultMatchers))
                .andReturn().getResponse();
    }

    private void createObjectMapper() {
        objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T> T getObjectFromJsonBody(HttpServletResponse response, Class<T> clazz) throws IOException {

        String jsonBody = ((MockHttpServletResponse) response).getContentAsString();

        return objectMapper.reader().forType(clazz).readValue(jsonBody);
    }

}
