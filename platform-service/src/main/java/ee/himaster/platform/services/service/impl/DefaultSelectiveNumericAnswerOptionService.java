package ee.himaster.platform.services.service.impl;

import ee.himaster.core.service.service.impl.AbstractModelService;
import ee.himaster.platform.services.model.quiz.answer.option.SelectiveNumericAnswerOptionModel;
import ee.himaster.platform.services.repository.SelectiveNumericAnswerOptionRepository;
import ee.himaster.platform.services.service.AnswerOptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultSelectiveNumericAnswerOptionService extends AbstractModelService<SelectiveNumericAnswerOptionModel> implements AnswerOptionService<SelectiveNumericAnswerOptionModel> {
    private final SelectiveNumericAnswerOptionRepository repository;

    @Override
    protected JpaRepository<SelectiveNumericAnswerOptionModel, Integer> getItemRepository() {
        return repository;
    }
}
