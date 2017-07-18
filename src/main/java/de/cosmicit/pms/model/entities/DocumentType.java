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
@Table(name = "tbl_document_type")
public class DocumentType {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "document_type_id", unique = true, nullable = false)
    private int id;

    @Column(name = "document_type_code")
    private String documentTypeCode;

    @Column(name = "document_type_description")
    private String documentTypeDescription;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "documentType")
    @JsonSerialize(using = CollectionSerializer.class)
    @JsonDeserialize(using = CollectionDeserializer.class)
    private Set<Document> documents = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumentTypeCode() {
        return documentTypeCode;
    }

    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }

    public String getDocumentTypeDescription() {
        return documentTypeDescription;
    }

    public void setDocumentTypeDescription(String documentTypeDescription) {
        this.documentTypeDescription = documentTypeDescription;
    }


    public void setDocuments(Set<Document> documents) {
        if (!this.documents.isEmpty()) {
            this.documents.forEach((Document document) -> document.setDocumentType(null));
            this.documents.clear();
        }
        documents.forEach((Document document) -> document.setDocumentType(this));
        this.documents.addAll(documents);
    }

    public void addDocument(Document document) {
        if (!this.documents.contains(document)) {
            this.documents.add(document);
        }
    }
}
