package ee.himaster.platform.services.model;

import ee.himaster.core.localization.model.LocalizedStringModel;
import ee.himaster.core.service.model.ItemModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "CITY")
@Data
@EqualsAndHashCode(callSuper = true)
public class CityModel extends ItemModel {

    @ManyToOne
    @JoinColumn
    private CountryModel country;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "title")
    private LocalizedStringModel title;
}
