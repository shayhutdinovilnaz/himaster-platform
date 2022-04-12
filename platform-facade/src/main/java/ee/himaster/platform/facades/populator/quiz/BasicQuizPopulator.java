package ee.himaster.platform.facades.populator.quiz;

import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.QuizDto;
import ee.himaster.platform.services.model.quiz.QuizModel;

public class BasicQuizPopulator implements Populator<QuizDto, QuizModel> {
    @Override
    public QuizDto populate(QuizModel source, QuizDto target) {
        return null;
    }

    @Override
    public QuizModel reversePopulate(QuizDto source, QuizModel target) {
        return null;
    }
}
