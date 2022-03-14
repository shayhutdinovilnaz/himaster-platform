package ee.himaster.platform.services.model;

import ee.himaster.core.service.model.ItemModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity(name = "COUNTRY")
@Data
@EqualsAndHashCode(callSuper = true)
public class CountryModel extends ItemModel {

    @Column
    private String isoCode;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CityModel> cities;
}
