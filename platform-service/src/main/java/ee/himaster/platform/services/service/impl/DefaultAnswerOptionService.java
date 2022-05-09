package ee.himaster.platform.services.service.impl;

import ee.himaster.core.service.service.impl.AbstractModelService;
import ee.himaster.platform.services.model.quiz.answer.AnswerOptionModel;
import ee.himaster.platform.services.repository.AnswerOptionRepository;
import ee.himaster.platform.services.service.AnswerOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultAnswerOptionService extends AbstractModelService<AnswerOptionModel> implements AnswerOptionService {
    private final AnswerOptionRepository answerOptionRepository;

    @Override
    protected JpaRepository<AnswerOptionModel, Integer> getItemRepository() {
        return answerOptionRepository;
    }
}
