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
@Table(name = "tbl_service_status")
public class ServiceStatus {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(name = "service_status_code")
    private String serviceStatusCode;

    @Column(name = "service_status_description")
    private String serviceStatusDescription;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceStatus")
    @JsonSerialize(using = CollectionSerializer.class)
    @JsonDeserialize(using = CollectionDeserializer.class)
    private Set<ServiceRequest> serviceRequests = new HashSet<>();

    public void setServiceRequests(Set<ServiceRequest> serviceRequests) {
        if (!this.serviceRequests.isEmpty()) {
            this.serviceRequests.forEach((ServiceRequest serviceRequest) -> serviceRequest.setServiceStatus(null));
            this.serviceRequests.clear();
        }
        serviceRequests.forEach((ServiceRequest serviceRequest) -> serviceRequest.setServiceStatus(this));
        this.serviceRequests.addAll(serviceRequests);
    }

    public void addServiceRequest(ServiceRequest serviceRequest) {
        if (!this.serviceRequests.contains(serviceRequest)) {
            this.serviceRequests.add(serviceRequest);
        }
    }
}
