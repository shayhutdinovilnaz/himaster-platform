package ee.himaster.platform.services.service;

import ee.himaster.platform.services.model.quiz.answer.AnswerModel;
import ee.himaster.platform.services.model.quiz.answer.AnswerValueModel;

import java.util.List;

public interface AnswerService {

    String getAnswerAsString(AnswerModel answer);

    List<AnswerValueModel> getAnswerFromString(String values);
}
