package com.moulik.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "certificate_record")
public class certiRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String certificateId;

    private String certificateName;
    @Column(name = "\"cast\"")
    private String cast ;
    private Long income;
    private String doctype ;
    private String isuser;
    private LocalDate issueDate;
    private boolean verifiedByAdmin = false;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    @PreUpdate
    public void setLastUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

    public String getIsuser() {
        return isuser;
    }

    public void setIsuser(String isuser) {
        this.isuser = isuser;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public boolean isVerifiedByAdmin() {
        return verifiedByAdmin;
    }

    public void setVerifiedByAdmin(boolean verifiedByAdmin) {
        this.verifiedByAdmin = verifiedByAdmin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public certiRecord(Long id, String certificateId, String certificateName, String cast, Long income, String doctype, String isuser, LocalDate issueDate, boolean verifiedByAdmin, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.certificateId = certificateId;
        this.certificateName = certificateName;
        this.cast = cast;
        this.income = income;
        this.doctype = doctype;
        this.isuser = isuser;
        this.issueDate = issueDate;
        this.verifiedByAdmin = verifiedByAdmin;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public certiRecord() {

    }
}
