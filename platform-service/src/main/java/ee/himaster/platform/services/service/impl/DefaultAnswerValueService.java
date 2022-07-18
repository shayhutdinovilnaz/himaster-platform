package ee.himaster.platform.services.service.impl;

import ee.himaster.platform.services.model.quiz.answer.AnswerModel;
import ee.himaster.platform.services.model.quiz.answer.AnswerValueModel;
import ee.himaster.platform.services.repository.FacetRepository;
import ee.himaster.platform.services.service.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class DefaultAnswerValueService implements AnswerService {
    private static final String FACET = "FACET";
    private static final String DELIMITER = "_DELIMITER_";
    private final FacetRepository facetRepository;

    @Override
    public String getAnswerAsString(AnswerModel answer) {
        return answer.getValues()
                .stream()
                .map(v -> FACET + "_" + v.getFacet().getCode().toUpperCase() + ":" + v.getValue())
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public List<AnswerValueModel> getAnswerFromString(String value) {
        var result = new ArrayList<AnswerValueModel>();
        for (String s : value.split(DELIMITER)) {
            //TODO by regex
            final var split = s.split(":");
            if (split.length > 1) {
                final var facetCode = split[0].replace(FACET + "_", "");
                final var facetValue = s.replace(FACET + "_" + facetCode, "");
                final var facet = facetRepository.getByCode(facetCode);
                if (facet != null) {
                    final var av = new AnswerValueModel();
                    av.setValue(facetValue);
                    av.setFacet(facet);
                    result.add(av);
                } else {
                    log.error("The facet by code is not found. Facet code: {}" + facetCode);
                }
            } else {
                log.error("The facet values has not been found.");
            }
        }

        return result;
    }

}
