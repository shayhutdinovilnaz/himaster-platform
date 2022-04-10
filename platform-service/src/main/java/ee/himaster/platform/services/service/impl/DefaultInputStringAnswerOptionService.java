package ee.himaster.platform.services.service.impl;

import ee.himaster.core.service.service.impl.AbstractModelService;
import ee.himaster.platform.services.model.quiz.answer.option.InputStringAnswerOptionModel;
import ee.himaster.platform.services.repository.InputStringAnswerOptionRepository;
import ee.himaster.platform.services.service.InputStringAnswerOptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultInputStringAnswerOptionService extends AbstractModelService<InputStringAnswerOptionModel> implements InputStringAnswerOptionService {
    private final InputStringAnswerOptionRepository repository;

    @Override
    protected JpaRepository<InputStringAnswerOptionModel, Integer> getItemRepository() {
        return repository;
    }
}
