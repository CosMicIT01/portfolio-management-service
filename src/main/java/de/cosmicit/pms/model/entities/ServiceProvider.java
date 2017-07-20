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
@Table(name = "tbl_service_provider")
public class ServiceProvider {



    @javax.persistence.Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "service_provider_id", unique = true, nullable = false)
    private Long Id;

    @Column(name = "service_provider_name")
    private String name;

    @Column(name = "service_provider_region")
    private String region;

    @Column(name = "service_provider_domain")
    private String domain;

    @Column(name = "service_provider_registration_number")
    private String registrationNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceProvider")
    @JsonSerialize(using = CollectionSerializer.class)
    @JsonDeserialize(using = CollectionDeserializer.class)
    private Set<Service> services = new HashSet<>();

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        if (!this.services.isEmpty()) {
            this.services.forEach((Service service) -> service.setServiceProvider(null));
            this.services.clear();
        }
        services.forEach((Service service) -> service.setServiceProvider(this));
        this.services.addAll(services);
    }

    public void addService(Service service) {
        if (!this.services.contains(service)) {
            this.services.add(service);
        }
    }
}
