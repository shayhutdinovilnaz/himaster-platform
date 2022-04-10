package ee.himaster.platform.services.service.impl;

import ee.himaster.core.service.service.impl.AbstractModelService;
import ee.himaster.platform.services.model.quiz.answer.option.SelectiveBooleanAnswerOptionModel;
import ee.himaster.platform.services.repository.SelectiveBooleanAnswerOptionRepository;
import ee.himaster.platform.services.service.SelectiveBooleanAnswerOptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultSelectiveBooleanAnswerOptionService extends AbstractModelService<SelectiveBooleanAnswerOptionModel> implements SelectiveBooleanAnswerOptionService {
    private final SelectiveBooleanAnswerOptionRepository repository;

    @Override
    protected JpaRepository<SelectiveBooleanAnswerOptionModel, Integer> getItemRepository() {
        return repository;
    }
}
