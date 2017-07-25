package de.cosmicit.pms.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.cosmicit.pms.model.deserializers.ReferenceDeserializer;
import de.cosmicit.pms.model.deserializers.UTCDateTimeDeserializer;
import de.cosmicit.pms.model.serializers.UTCDateTimeSerializer;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tbl_document")
public class Document {

    public enum ApprovalStatus {
        PENDING,
        REJECTED,
        APPROVED;

        @JsonValue
        public String value() {
            return this.name().toLowerCase();
        }
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "document_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "document_name")
    private String name;

    @Column(name = "document_url")
    private String url;

    @Column(name = "signed_by")
    private String signedBy;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "approval_status")
    private ApprovalStatus approvalStatus;

    @Column(name = "creation_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime",
            parameters = {@org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"), @org.hibernate.annotations.Parameter(name = "javaZone", value = "jvm")})
    @JsonDeserialize(using = UTCDateTimeDeserializer.class)
    @JsonSerialize(using = UTCDateTimeSerializer.class)
    private DateTime creationDate;

    @Column(name = "approval_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime",
            parameters = {@org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"), @org.hibernate.annotations.Parameter(name = "javaZone", value = "jvm")})
    @JsonDeserialize(using = UTCDateTimeDeserializer.class)
    @JsonSerialize(using = UTCDateTimeSerializer.class)
    private DateTime approvalDate;

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

    public String getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(String signedBy) {
        this.signedBy = signedBy;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public DateTime getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(DateTime approvalDate) {
        this.approvalDate = approvalDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }
}
