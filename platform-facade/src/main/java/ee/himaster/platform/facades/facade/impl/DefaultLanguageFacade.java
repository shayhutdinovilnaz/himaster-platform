package ee.himaster.platform.facades.facade.impl;

import ee.himaster.core.service.converter.impl.BasicConverter;
import ee.himaster.platform.dto.LanguageDto;
import ee.himaster.platform.facades.facade.LanguageFacade;
import ee.himaster.platform.services.model.LanguageModel;
import ee.himaster.platform.services.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DefaultLanguageFacade implements LanguageFacade {
    private final LanguageService languageService;
    private final BasicConverter<LanguageDto, LanguageModel> languageConverter;

    @Override
    public List<LanguageDto> getLanguages(final String countryIsoCode) {
        return languageService.getByCountry(countryIsoCode)
                .stream()
                .map(languageConverter::convert)
                .sorted((a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()))
                .collect(Collectors.toList());
    }
}
