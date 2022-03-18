package ee.himaster.platform.facades.populator;

import ee.himaster.core.localization.model.LocaleModel;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.LocaleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BasicLocalePopulator implements Populator<LocaleDto, LocaleModel> {

    @Override
    public LocaleDto populate(final LocaleModel source, final LocaleDto target) {
        target.setCode(source.getCode());
        target.setTimeZoneId(source.getTimeZone().getID());
        target.setCcy(source.getCurrency().getCode());
        target.setLanguageIsoCode(source.getLanguage().getIsoCode());
        return target;
    }

    @Override
    public LocaleModel reversePopulate(final LocaleDto source, final LocaleModel target) {
        return target;
    }
}
