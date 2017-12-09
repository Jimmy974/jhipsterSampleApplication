package io.github.jhipster.application.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AppointmentInfo entity.
 */
public class AppointmentInfoDTO implements Serializable {

    private Long id;

    @NotNull
    private Long trn;

    @NotNull
    private String groupMemId;

    @NotNull
    private String quotaId;

    @NotNull
    private String serviceType;

    @NotNull
    private String apmidType;

    @NotNull
    private String apmidCode;

    private LocalDate appDob;

    @NotNull
    private String servicesNature;

    @NotNull
    private String ageInd;

    @NotNull
    private String prefilInd;

    @NotNull
    private String active;

    @NotNull
    private LocalDate createDate;

    @NotNull
    private String createBy;

    @NotNull
    private LocalDate lastUpdBy;

    @NotNull
    private String lastUpdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrn() {
        return trn;
    }

    public void setTrn(Long trn) {
        this.trn = trn;
    }

    public String getGroupMemId() {
        return groupMemId;
    }

    public void setGroupMemId(String groupMemId) {
        this.groupMemId = groupMemId;
    }

    public String getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(String quotaId) {
        this.quotaId = quotaId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getApmidType() {
        return apmidType;
    }

    public void setApmidType(String apmidType) {
        this.apmidType = apmidType;
    }

    public String getApmidCode() {
        return apmidCode;
    }

    public void setApmidCode(String apmidCode) {
        this.apmidCode = apmidCode;
    }

    public LocalDate getAppDob() {
        return appDob;
    }

    public void setAppDob(LocalDate appDob) {
        this.appDob = appDob;
    }

    public String getServicesNature() {
        return servicesNature;
    }

    public void setServicesNature(String servicesNature) {
        this.servicesNature = servicesNature;
    }

    public String getAgeInd() {
        return ageInd;
    }

    public void setAgeInd(String ageInd) {
        this.ageInd = ageInd;
    }

    public String getPrefilInd() {
        return prefilInd;
    }

    public void setPrefilInd(String prefilInd) {
        this.prefilInd = prefilInd;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDate getLastUpdBy() {
        return lastUpdBy;
    }

    public void setLastUpdBy(LocalDate lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
    }

    public String getLastUpdDate() {
        return lastUpdDate;
    }

    public void setLastUpdDate(String lastUpdDate) {
        this.lastUpdDate = lastUpdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AppointmentInfoDTO appointmentInfoDTO = (AppointmentInfoDTO) o;
        if(appointmentInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appointmentInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AppointmentInfoDTO{" +
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
