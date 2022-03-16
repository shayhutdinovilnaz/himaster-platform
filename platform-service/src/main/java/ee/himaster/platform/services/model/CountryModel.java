package ee.himaster.platform.services.model;

import ee.himaster.core.service.model.ItemModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "country")
public class CountryModel extends ItemModel {

    @Column(unique = true)
    private String isoCode;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CityModel> cities;

    @ManyToMany
    @JoinTable(
            name = "COUNTRY_LANGUAGE",
            joinColumns = @JoinColumn(name = "country_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<LanguageModel> languages;
}
