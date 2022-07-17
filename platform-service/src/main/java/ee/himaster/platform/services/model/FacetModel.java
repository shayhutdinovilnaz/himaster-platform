package ee.himaster.platform.services.model;

import ee.himaster.core.localization.model.LocalizedStringModel;
import ee.himaster.core.service.model.ItemModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Getter
@Setter
@Entity(name = "facet")
public class FacetModel extends ItemModel {

    private String code;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "name")
    private LocalizedStringModel name;

    private String type;
}
