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
@Table(name = "tbl_subscription_type")
public class SubscriptionType {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "subscription_type_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "subscription_type_code")
    private String subscriptionTypeCode;

    @Column(name = "subscription_type_description")
    private String subscriptionTypeDescription;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subscriptionType")
    @JsonSerialize(using = CollectionSerializer.class)
    @JsonDeserialize(using = CollectionDeserializer.class)
    private Set<Subscription> subscriptions = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubscriptionTypeCode() {
        return subscriptionTypeCode;
    }

    public void setSubscriptionTypeCode(String subscriptionTypeCode) {
        this.subscriptionTypeCode = subscriptionTypeCode;
    }

    public String getSubscriptionTypeDescription() {
        return subscriptionTypeDescription;
    }

    public void setSubscriptionTypeDescription(String subscriptionTypeDescription) {
        this.subscriptionTypeDescription = subscriptionTypeDescription;
    }


    public void setSubscriptions(Set<Subscription> subscriptions) {
        if (!this.subscriptions.isEmpty()) {
            this.subscriptions.forEach((Subscription subscription) -> subscription.setSubscriptionType(null));
            this.subscriptions.clear();
        }
        subscriptions.forEach((Subscription subscription) -> subscription.setSubscriptionType(this));
        this.subscriptions.addAll(subscriptions);
    }

    public void addSubscription(Subscription subscription) {
        if (!this.subscriptions.contains(subscription)) {
            this.subscriptions.add(subscription);
        }
    }
}
