package de.cosmicit.pms.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.cosmicit.pms.model.deserializers.CollectionDeserializer;
import de.cosmicit.pms.model.deserializers.ReferenceDeserializer;
import de.cosmicit.pms.model.deserializers.UTCDateTimeDeserializer;
import de.cosmicit.pms.model.serializers.CollectionSerializer;
import de.cosmicit.pms.model.serializers.UTCDateTimeSerializer;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * This class represents the Service Entity of the Application
 */
@Entity
@Table(name = "tbl_service_contract")
public class ServiceContract {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "contract_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "start_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime",
            parameters = {@org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"), @org.hibernate.annotations.Parameter(name = "javaZone", value = "jvm")})
    @JsonDeserialize(using = UTCDateTimeDeserializer.class)
    @JsonSerialize(using = UTCDateTimeSerializer.class)
    private DateTime startDate;

    @Column(name = "end_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime",
            parameters = {@org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"), @org.hibernate.annotations.Parameter(name = "javaZone", value = "jvm")})
    @JsonDeserialize(using = UTCDateTimeDeserializer.class)
    @JsonSerialize(using = UTCDateTimeSerializer.class)
    private DateTime endDate;

    @Column(name = "contract_duration")
    private int duration;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceContract")
    @JsonSerialize(using = CollectionSerializer.class)
    @JsonDeserialize(using = CollectionDeserializer.class)
    private Set<Document> documents= new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "service_contract_link_service_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonDeserialize(using = ReferenceDeserializer.class)
    private Service service;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        if (!this.documents.isEmpty()) {
            this.documents.forEach((Document document) -> document.setServiceContract(null));
            this.documents.clear();
        }
        documents.forEach((Document document) -> document.setServiceContract(this));
        this.documents.addAll(documents);
    }

    public void addDocument(Document document) {
        if (!this.documents.contains(document)) {
            this.documents.add(document);
        }
    }
}
