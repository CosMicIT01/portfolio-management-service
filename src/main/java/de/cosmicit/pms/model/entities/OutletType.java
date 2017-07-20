package de.cosmicit.pms.model.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.cosmicit.pms.model.deserializers.CollectionDeserializer;
import de.cosmicit.pms.model.serializers.CollectionSerializer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tbl_outlet_type")
public class OutletType {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "outlet_type_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "outlet_type_code")
    private String outletTypeCode;

    @Column(name = "outlet_type_description")
    private String outletTypeDescription;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "outletType")
    @JsonSerialize(using = CollectionSerializer.class)
    @JsonDeserialize(using = CollectionDeserializer.class)
    private Set<Outlet> outlets = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutletTypeCode() {
        return outletTypeCode;
    }

    public void setOutletTypeCode(String outletTypeCode) {
        this.outletTypeCode = outletTypeCode;
    }

    public String getOutletTypeDescription() {
        return outletTypeDescription;
    }

    public void setOutletTypeDescription(String outletTypeDescription) {
        this.outletTypeDescription = outletTypeDescription;
    }

    public Set<Outlet> getOutlets() {
        return outlets;
    }

    public void setOutlets(Set<Outlet> outlets) {
        if (!this.outlets.isEmpty()) {
            this.outlets.forEach((Outlet outlet) -> outlet.setOutletType(null));
            this.outlets.clear();
        }
        outlets.forEach((Outlet outlet) -> outlet.setOutletType(this));
        this.outlets.addAll(outlets);
    }

    public void addOutlet(Outlet outlet) {
        if (!this.outlets.contains(outlet)) {
            this.outlets.add(outlet);
        }
    }
}
