package ee.himaster.platform.facades.populator;

import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.QuestionDto;
import ee.himaster.platform.services.model.quiz.QuestionModel;

public class BasicQuestionPopulator implements Populator<QuestionModel, QuestionDto> {
    @Override
    public QuestionModel populate(QuestionDto source, QuestionModel target) {
        return null;
    }

    @Override
    public QuestionDto reversePopulate(QuestionModel source, QuestionDto target) {
        return null;
    }
}
