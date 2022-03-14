package ee.himaster.platform.services.service.impl;

import ee.himaster.core.service.service.impl.AbstractModelService;
import ee.himaster.platform.services.model.LanguageModel;
import ee.himaster.platform.services.repository.LanguageRepository;
import ee.himaster.platform.services.service.LanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultLanguageService extends AbstractModelService<LanguageModel> implements LanguageService {
    private final LanguageRepository languageRepository;

    @Override
    protected JpaRepository<LanguageModel, Integer> getItemRepository() {
        return languageRepository;
    }
}
