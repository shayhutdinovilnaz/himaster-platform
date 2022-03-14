package ee.himaster.platform.services.model;

import ee.himaster.core.localization.model.LocalizedStringModel;
import ee.himaster.core.service.model.ItemModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity(name = "LANGUAGE")
@Data
@EqualsAndHashCode(callSuper = true)
public class LanguageModel extends ItemModel {

    @Column(name = "isoCode", nullable = false)
    private String isoCode;

    @ManyToMany
    @JoinTable(
            name = "LANGUAGE_COUNTRIES",
            joinColumns = @JoinColumn(name = "language_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private List<CountryModel> countries;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "title")
    private LocalizedStringModel title;
}
