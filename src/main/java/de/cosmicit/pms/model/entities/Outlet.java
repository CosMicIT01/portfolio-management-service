package de.cosmicit.pms.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.cosmicit.pms.model.deserializers.CollectionDeserializer;
import de.cosmicit.pms.model.deserializers.ReferenceDeserializer;
import de.cosmicit.pms.model.serializers.CollectionSerializer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * This class represents the Agent Entity of the Application
 */
@Entity
@Table(name = "tbl_outlet")
public class Outlet {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "outlet_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "outlet_name")
    private String outletName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agent")
    @JsonSerialize(using = CollectionSerializer.class)
    @JsonDeserialize(using = CollectionDeserializer.class)
    private Set<ServiceRequest> serviceRequests = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "outlet_link_outlet_type_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonDeserialize(using = ReferenceDeserializer.class)
    private OutletType outletType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public OutletType getOutletType() {
        return outletType;
    }

    public void setOutletType(OutletType outletType) {
        this.outletType = outletType;
    }

    public void setServiceRequests(Set<ServiceRequest> serviceRequests) {
        if (!this.serviceRequests.isEmpty()) {
            this.serviceRequests.forEach((ServiceRequest serviceRequest) -> serviceRequest.setOutlet(null));
            this.serviceRequests.clear();
        }
        serviceRequests.forEach((ServiceRequest serviceRequest) -> serviceRequest.setOutlet(this));
        this.serviceRequests.addAll(serviceRequests);
    }

    public void addServiceRequest(ServiceRequest serviceRequest) {
        if (!this.serviceRequests.contains(serviceRequest)) {
            this.serviceRequests.add(serviceRequest);
        }
    }


}
