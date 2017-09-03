package de.cosmicit.pms.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.cosmicit.pms.model.deserializers.ReferenceDeserializer;
import de.cosmicit.pms.model.deserializers.UTCDateTimeDeserializer;
import de.cosmicit.pms.model.serializers.UTCDateTimeSerializer;
import org.hibernate.annotations.*;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Parameter;
import javax.persistence.Table;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tbl_subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "subscription_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "subscription_name")
    private String name;

    @Column(name = "creation_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime",
            parameters = {@org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"), @org.hibernate.annotations.Parameter(name = "javaZone", value = "jvm")})
    @JsonDeserialize(using = UTCDateTimeDeserializer.class)
    @JsonSerialize(using = UTCDateTimeSerializer.class)
    private DateTime creationDate;

    @Column(name = "expiry_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime",
            parameters = {@org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"), @org.hibernate.annotations.Parameter(name = "javaZone", value = "jvm")})
    @JsonDeserialize(using = UTCDateTimeDeserializer.class)
    @JsonSerialize(using = UTCDateTimeSerializer.class)
    private DateTime expiryDate;

    @Column(name = "renewal_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime",
            parameters = {@org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"), @org.hibernate.annotations.Parameter(name = "javaZone", value = "jvm")})
    @JsonDeserialize(using = UTCDateTimeDeserializer.class)
    @JsonSerialize(using = UTCDateTimeSerializer.class)
    private DateTime renewalDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "subscription_link_subscription_type_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonDeserialize(using = ReferenceDeserializer.class)
    private SubscriptionType subscriptionType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "subscription_link_service_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonDeserialize(using = ReferenceDeserializer.class)
    private Service service;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "subscription_link_customer_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonDeserialize(using = ReferenceDeserializer.class)
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public DateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(DateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public DateTime getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(DateTime renewalDate) {
        this.renewalDate = renewalDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
