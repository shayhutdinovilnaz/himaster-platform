package ee.himaster.platform.facades.populator;

import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.QuizItemDto;
import ee.himaster.platform.services.model.quiz.QuizItemModel;

public class BasicQuizItemPopulator implements Populator<QuizItemDto, QuizItemModel> {
    @Override
    public QuizItemDto populate(QuizItemModel source, QuizItemDto target) {
        return null;
    }

    @Override
    public QuizItemModel reversePopulate(QuizItemDto source, QuizItemModel target) {
        return null;
    }
}
