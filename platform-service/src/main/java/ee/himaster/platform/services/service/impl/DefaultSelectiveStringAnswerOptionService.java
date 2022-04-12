package ee.himaster.platform.services.service.impl;

import ee.himaster.core.service.service.impl.AbstractModelService;
import ee.himaster.platform.services.model.quiz.answer.option.SelectiveStringAnswerOptionModel;
import ee.himaster.platform.services.repository.SelectiveStringAnswerOptionRepository;
import ee.himaster.platform.services.service.SelectiveAnswerOptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultSelectiveStringAnswerOptionService extends AbstractModelService<SelectiveStringAnswerOptionModel> implements SelectiveAnswerOptionService <SelectiveStringAnswerOptionModel>{
    private final SelectiveStringAnswerOptionRepository repository;

    @Override
    protected JpaRepository<SelectiveStringAnswerOptionModel, Integer> getItemRepository() {
        return repository;
    }
}
