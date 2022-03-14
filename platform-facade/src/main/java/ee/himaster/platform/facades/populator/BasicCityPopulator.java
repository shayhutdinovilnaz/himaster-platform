package ee.himaster.platform.facades.populator;

import ee.himaster.core.localization.service.LocalizedStringService;
import ee.himaster.core.service.populator.Populator;
import ee.himaster.platform.dto.CityDto;
import ee.himaster.platform.services.model.CityModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BasicCityPopulator implements Populator<CityDto, CityModel> {
    private final LocalizedStringService localizedStringService;

    @Override
    public CityDto populate(final CityModel source, final CityDto target) {
        target.setTitle(localizedStringService.getLocalizedStringValue(source.getTitle()));
        return target;
    }

    @Override
    public CityModel reversePopulate(final CityDto source, final CityModel target) {
        target.setTitle(localizedStringService.addLocalizedStringValue(source.getTitle(), target.getTitle()));
        return target;
    }
}
