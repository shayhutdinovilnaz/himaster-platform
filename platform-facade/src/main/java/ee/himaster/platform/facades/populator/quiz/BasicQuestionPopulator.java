package ee.himaster.platform.facades.populator.quiz;

import ee.himaster.core.localization.service.LocalizedStringService;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.QuestionDto;
import ee.himaster.platform.services.model.quiz.QuestionModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BasicQuestionPopulator implements Populator<QuestionModel, QuestionDto> {
    private final LocalizedStringService localizedStringService;

    @Override
    public QuestionModel populate(QuestionDto source, QuestionModel target) {
        target.setId(source.getId());
        target.setTitle(localizedStringService.addLocalizedStringValue(source.getTitle(), target.getTitle()));
//        target.setAnswerOptions();
        return null;
    }

    @Override
    public QuestionDto reversePopulate(QuestionModel source, QuestionDto target) {
        return null;
    }
}
