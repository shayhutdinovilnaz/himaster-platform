package ee.himaster.platform.services.service.impl;

import ee.himaster.platform.services.model.quiz.answer.AnswerModel;
import ee.himaster.platform.services.model.quiz.answer.AnswerValueModel;
import ee.himaster.platform.services.repository.FacetRepository;
import ee.himaster.platform.services.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DefaultAnswerValueService implements AnswerService {
    private final FacetRepository facetRepository;

    @Override
    public String getAnswerAsString(AnswerModel answer) {
        return answer.getValues()
                .stream()
                .map(v -> "FACET_" + v.getFacet().getCode().toUpperCase() + ":" + v.getValue())
                .collect(Collectors.joining("_DELIMITER_"));
    }

    @Override
    public List<AnswerValueModel> getAnswerFromString(String value) {
        final var values = value.split("_DELIMITER_");
        return new ArrayList<>();
    }
}
