package de.cosmicit.pms.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.cosmicit.pms.model.deserializers.CollectionDeserializer;
import de.cosmicit.pms.model.serializers.CollectionSerializer;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * This class represents the Agent Entity of the Application
 */
@Entity
@Table(name = "tbl_agent")
public class Agent {

    public enum Gender {
        FEMALE,
        MALE;

        @JsonValue
        public String value() {
            return this.name().toLowerCase();
        }
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "agent_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "agent_name")
    private String agentName;

    @Column(name = "agent_firstname")
    private String firstName;

    @Column(name = "agent_lastname")
    private String lastName;

    @Column(name = "agent_email")
    private String email;

    @Column(name = "agent_birthdate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Column(name = "agent_gender")
    private Agent.Gender gender;

    @Column(name = "agent_street")
    private String street;

    @Column(name = "agent_housenumber")
    private String houseNumber;

    @Column(name = "agent_zipcode")
    private String zipcode;

    @Column(name = "agent_city")
    private String city;

    @Column(name = "agent_country")
    private String country;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agent")
    @JsonSerialize(using = CollectionSerializer.class)
    @JsonDeserialize(using = CollectionDeserializer.class)
    private Set<ServiceRequest> serviceRequests = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public Set<ServiceRequest> getServiceRequests() {
        return serviceRequests;
    }

    public void setServiceRequests(Set<ServiceRequest> serviceRequests) {
        if (!this.serviceRequests.isEmpty()) {
            this.serviceRequests.forEach((ServiceRequest serviceRequest) -> serviceRequest.setAgent(null));
            this.serviceRequests.clear();
        }
        serviceRequests.forEach((ServiceRequest serviceRequest) -> serviceRequest.setAgent(this));
        this.serviceRequests.addAll(serviceRequests);
    }

    public void addServiceRequest(ServiceRequest serviceRequest) {
        if (!this.serviceRequests.contains(serviceRequest)) {
            this.serviceRequests.add(serviceRequest);
        }
    }
}
