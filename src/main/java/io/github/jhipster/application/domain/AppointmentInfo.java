package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A AppointmentInfo.
 */
@Entity
@Table(name = "appointment_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AppointmentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "trn", nullable = false)
    private Long trn;

    @NotNull
    @Column(name = "group_mem_id", nullable = false)
    private String groupMemId;

    @NotNull
    @Column(name = "quota_id", nullable = false)
    private String quotaId;

    @NotNull
    @Column(name = "service_type", nullable = false)
    private String serviceType;

    @NotNull
    @Column(name = "apmid_type", nullable = false)
    private String apmidType;

    @NotNull
    @Column(name = "apmid_code", nullable = false)
    private String apmidCode;

    @Column(name = "app_dob")
    private LocalDate appDob;

    @NotNull
    @Column(name = "services_nature", nullable = false)
    private String servicesNature;

    @NotNull
    @Column(name = "age_ind", nullable = false)
    private String ageInd;

    @NotNull
    @Column(name = "prefil_ind", nullable = false)
    private String prefilInd;

    @NotNull
    @Column(name = "active", nullable = false)
    private String active;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @NotNull
    @Column(name = "create_by", nullable = false)
    private String createBy;

    @NotNull
    @Column(name = "last_upd_by", nullable = false)
    private LocalDate lastUpdBy;

    @NotNull
    @Column(name = "last_upd_date", nullable = false)
    private String lastUpdDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrn() {
        return trn;
    }

    public AppointmentInfo trn(Long trn) {
        this.trn = trn;
        return this;
    }

    public void setTrn(Long trn) {
        this.trn = trn;
    }

    public String getGroupMemId() {
        return groupMemId;
    }

    public AppointmentInfo groupMemId(String groupMemId) {
        this.groupMemId = groupMemId;
        return this;
    }

    public void setGroupMemId(String groupMemId) {
        this.groupMemId = groupMemId;
    }

    public String getQuotaId() {
        return quotaId;
    }

    public AppointmentInfo quotaId(String quotaId) {
        this.quotaId = quotaId;
        return this;
    }

    public void setQuotaId(String quotaId) {
        this.quotaId = quotaId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public AppointmentInfo serviceType(String serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getApmidType() {
        return apmidType;
    }

    public AppointmentInfo apmidType(String apmidType) {
        this.apmidType = apmidType;
        return this;
    }

    public void setApmidType(String apmidType) {
        this.apmidType = apmidType;
    }

    public String getApmidCode() {
        return apmidCode;
    }

    public AppointmentInfo apmidCode(String apmidCode) {
        this.apmidCode = apmidCode;
        return this;
    }

    public void setApmidCode(String apmidCode) {
        this.apmidCode = apmidCode;
    }

    public LocalDate getAppDob() {
        return appDob;
    }

    public AppointmentInfo appDob(LocalDate appDob) {
        this.appDob = appDob;
        return this;
    }

    public void setAppDob(LocalDate appDob) {
        this.appDob = appDob;
    }

    public String getServicesNature() {
        return servicesNature;
    }

    public AppointmentInfo servicesNature(String servicesNature) {
        this.servicesNature = servicesNature;
        return this;
    }

    public void setServicesNature(String servicesNature) {
        this.servicesNature = servicesNature;
    }

    public String getAgeInd() {
        return ageInd;
    }

    public AppointmentInfo ageInd(String ageInd) {
        this.ageInd = ageInd;
        return this;
    }

    public void setAgeInd(String ageInd) {
        this.ageInd = ageInd;
    }

    public String getPrefilInd() {
        return prefilInd;
    }

    public AppointmentInfo prefilInd(String prefilInd) {
        this.prefilInd = prefilInd;
        return this;
    }

    public void setPrefilInd(String prefilInd) {
        this.prefilInd = prefilInd;
    }

    public String getActive() {
        return active;
    }

    public AppointmentInfo active(String active) {
        this.active = active;
        return this;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public AppointmentInfo createDate(LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public AppointmentInfo createBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDate getLastUpdBy() {
        return lastUpdBy;
    }

    public AppointmentInfo lastUpdBy(LocalDate lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
        return this;
    }

    public void setLastUpdBy(LocalDate lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
    }

    public String getLastUpdDate() {
        return lastUpdDate;
    }

    public AppointmentInfo lastUpdDate(String lastUpdDate) {
        this.lastUpdDate = lastUpdDate;
        return this;
    }

    public void setLastUpdDate(String lastUpdDate) {
        this.lastUpdDate = lastUpdDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AppointmentInfo appointmentInfo = (AppointmentInfo) o;
        if (appointmentInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appointmentInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AppointmentInfo{" +
            "id=" + getId() +
            ", trn=" + getTrn() +
            ", groupMemId='" + getGroupMemId() + "'" +
            ", quotaId='" + getQuotaId() + "'" +
            ", serviceType='" + getServiceType() + "'" +
            ", apmidType='" + getApmidType() + "'" +
            ", apmidCode='" + getApmidCode() + "'" +
            ", appDob='" + getAppDob() + "'" +
            ", servicesNature='" + getServicesNature() + "'" +
            ", ageInd='" + getAgeInd() + "'" +
            ", prefilInd='" + getPrefilInd() + "'" +
            ", active='" + getActive() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createBy='" + getCreateBy() + "'" +
            ", lastUpdBy='" + getLastUpdBy() + "'" +
            ", lastUpdDate='" + getLastUpdDate() + "'" +
            "}";
    }
}
