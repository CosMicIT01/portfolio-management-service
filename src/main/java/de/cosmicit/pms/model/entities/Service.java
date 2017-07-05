package de.cosmicit.pms.model.entities;

import javax.persistence.*;
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
}
