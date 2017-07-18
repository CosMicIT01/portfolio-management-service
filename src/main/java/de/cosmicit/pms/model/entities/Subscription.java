package de.cosmicit.pms.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.cosmicit.pms.model.deserializers.ReferenceDeserializer;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tbl_subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "document_id", unique = true, nullable = false)
    private Long Id;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "signed_by")
    private String signedBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "document_link_document_type_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonDeserialize(using = ReferenceDeserializer.class)
    private DocumentType documentType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "document_link_service_request_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonDeserialize(using = ReferenceDeserializer.class)
    private ServiceRequest serviceRequest;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(String signedBy) {
        this.signedBy = signedBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }
}
