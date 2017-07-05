package de.cosmicit.pms.model.entities;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * This class represents the Service Contract Entity of the Application
 */
@Entity
@Table(name = "tbl_servicecontract")
public class ServiceContract {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "servicecontract_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "servicecontract_code")
    private String serviceCode;

    @Column(name = "servicecontract_servicename")
    private String serviceName;

    @Column(name = "servicecontract_expirydate")
    private Date expiryDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
