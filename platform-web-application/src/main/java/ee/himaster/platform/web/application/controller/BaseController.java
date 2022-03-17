package ee.himaster.platform.web.application.controller;

import ee.himaster.core.service.exception.ModelNotFoundException;
import ee.himaster.core.service.formatter.RequestExceptionHandlerFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class BaseController {
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(final ModelNotFoundException exception, final HttpServletRequest request) {
        final Map<String, Object> responseBody = RequestExceptionHandlerFormatter.format(exception, request, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }
}
