package ee.himaster.platform.services.service.impl;

import ee.himaster.core.service.service.impl.AbstractModelService;
import ee.himaster.platform.services.model.quiz.answer.option.InputNumericAnswerOptionModel;
import ee.himaster.platform.services.repository.InputNumericAnswerOptionRepository;
import ee.himaster.platform.services.service.AnswerOptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultInputNumericAnswerOptionService extends AbstractModelService<InputNumericAnswerOptionModel>
        implements AnswerOptionService<InputNumericAnswerOptionModel> {
    private final InputNumericAnswerOptionRepository repository;

    @Override
    protected JpaRepository<InputNumericAnswerOptionModel, Integer> getItemRepository() {
        return repository;
    }
}
