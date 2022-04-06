package ee.himaster.platform.facades.populator;

import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.AnswerDto;
import ee.himaster.platform.services.model.quiz.answer.AnswerModel;

public class BasicAnswerPopulator implements Populator<AnswerDto, AnswerModel> {
    @Override
    public AnswerDto populate(AnswerModel source, AnswerDto target) {
        return null;
    }

    @Override
    public AnswerModel reversePopulate(AnswerDto source, AnswerModel target) {
        return null;
    }
}
