package ee.himaster.platform.services.service.impl;

import ee.himaster.core.service.exception.ModelNotFoundException;
import ee.himaster.core.service.service.impl.AbstractModelService;
import ee.himaster.platform.services.model.CountryModel;
import ee.himaster.platform.services.model.LanguageModel;
import ee.himaster.platform.services.repository.CountryRepository;
import ee.himaster.platform.services.repository.LanguageRepository;
import ee.himaster.platform.services.service.LanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultLanguageService extends AbstractModelService<LanguageModel> implements LanguageService {
    private final LanguageRepository languageRepository;
    private final CountryRepository countryRepository;

    @Override
    public List<LanguageModel> getByCountry(String countryIsoCode) {
        return Optional.of(countryRepository.getByIsoCode(countryIsoCode))
                .map(CountryModel::getLanguages)
                .orElseThrow(() -> new ModelNotFoundException("Country is not found. Country code: " + countryIsoCode));
    }

    @Override
    protected JpaRepository<LanguageModel, Integer> getItemRepository() {
        return languageRepository;
    }
}
