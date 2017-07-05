package de.cosmicit.pms.model.entities;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * This class represents the Service Provider Entity of the Application
 *
 */
@Entity
@Table(name = "tbl_serviceprovider")
public class ServiceProvider {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "serviceprovider_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "serviceprovider_name")
    private String serviceproviderName;

    @Column(name = "serviceprovider_email")
    private String email;

    @Column(name = "serviceprovider_street")
    private String street;

    @Column(name = "serviceprovider_housenumber")
    private String houseNumber;

    @Column(name = "serviceprovider_zipcode")
    private String zipcode;

    @Column(name = "serviceprovider_city")
    private String city;

    @Column(name = "serviceprovider_country")
    private String country;

    @Column(name = "serviceprovider_companyurl")
    private String companyUrl;

    // TODO to add fields related to business like the registration number

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceproviderName() {
        return serviceproviderName;
    }

    public void setServiceproviderName(String serviceproviderName) {
        this.serviceproviderName = serviceproviderName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }
}
