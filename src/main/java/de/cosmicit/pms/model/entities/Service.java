package de.cosmicit.pms.model.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.cosmicit.pms.model.deserializers.CollectionDeserializer;
import de.cosmicit.pms.model.serializers.CollectionSerializer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * This class represents the Service Entity of the Application
 */
@Entity
@Table(name = "tbl_service")
public class Service {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "service_id", unique = true, nullable = false)
    private Long Id;

    @Column(name = "service_code")
    private String serviceCode;

    @Column(name = "service_description")
    private String serviceDescription;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "service")
    @JsonSerialize(using = CollectionSerializer.class)
    @JsonDeserialize(using = CollectionDeserializer.class)
    private Set<ServiceRequest> serviceRequests = new HashSet<>();

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public void setServiceRequests(Set<ServiceRequest> serviceRequests) {
        if (!this.serviceRequests.isEmpty()) {
            this.serviceRequests.forEach((ServiceRequest serviceRequest) -> serviceRequest.setService(null));
            this.serviceRequests.clear();
        }
        serviceRequests.forEach((ServiceRequest serviceRequest) -> serviceRequest.setService(this));
        this.serviceRequests.addAll(serviceRequests);
    }

    public void addServiceRequest(ServiceRequest serviceRequest) {
        if (!this.serviceRequests.contains(serviceRequest)) {
            this.serviceRequests.add(serviceRequest);
        }
    }
}
