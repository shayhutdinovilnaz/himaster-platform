package ee.himaster.platform.facades.converter;

import ee.himaster.core.localization.model.LocaleModel;
import ee.himaster.platform.dto.LocaleDto;
import org.springframework.core.convert.converter.Converter;

public class LocaleConverter implements Converter<LocaleModel, LocaleDto> {

    @Override
    public LocaleDto convert(final LocaleModel source) {
        var target = new LocaleDto();
        target.setCode(source.getCode());
        target.setTimeZoneId(source.getTimeZone().getID());
        target.setCcy(source.getCurrency().getCode());
        target.setLanguageIsoCode(source.getLanguage().getIsoCode());
        return target;
    }
}
