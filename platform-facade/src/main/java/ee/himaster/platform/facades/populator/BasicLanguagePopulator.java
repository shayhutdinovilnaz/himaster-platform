package ee.himaster.platform.facades.populator;

import ee.himaster.core.localization.service.LocalizedStringService;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.LanguageDto;
import ee.himaster.platform.services.model.LanguageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BasicLanguagePopulator implements Populator<LanguageDto, LanguageModel> {
    private final LocalizedStringService localizedStringService;

    @Override
    public LanguageDto populate(final LanguageModel source, final LanguageDto target) {
        target.setTitle(localizedStringService.getLocalizedStringValue(source.getTitle()));
        target.setIsoCode(source.getIsoCode());
        return target;
    }

    @Override
    public LanguageModel reversePopulate(final LanguageDto source, final LanguageModel target) {
        target.setTitle(localizedStringService.addLocalizedStringValue(source.getTitle(), target.getTitle()));
        target.setIsoCode(source.getIsoCode());
        return target;
    }
}
