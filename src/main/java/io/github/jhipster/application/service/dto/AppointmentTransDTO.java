package io.github.jhipster.application.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AppointmentTrans entity.
 */
public class AppointmentTransDTO implements Serializable {

    private Long id;

    @NotNull
    private Long ern;

    @NotNull
    private Long trn;

    private Long preErn;

    private Long preTrn;

    @NotNull
    private Integer appId;

    private String exportStatus;

    private String interfaceFile;

    private String controlFile;

    private LocalDate exportTime;

    @NotNull
    private Integer enqCode;

    @NotNull
    private LocalDate transTime;

    @NotNull
    private String commChannel;

    private String commParameter;

    @NotNull
    private String isNotSent;

    @NotNull
    private String isRemSent;

    @NotNull
    private String virtualInd;

    @NotNull
    private String action;

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

    public Long getErn() {
        return ern;
    }

    public void setErn(Long ern) {
        this.ern = ern;
    }

    public Long getTrn() {
        return trn;
    }

    public void setTrn(Long trn) {
        this.trn = trn;
    }

    public Long getPreErn() {
        return preErn;
    }

    public void setPreErn(Long preErn) {
        this.preErn = preErn;
    }

    public Long getPreTrn() {
        return preTrn;
    }

    public void setPreTrn(Long preTrn) {
        this.preTrn = preTrn;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getExportStatus() {
        return exportStatus;
    }

    public void setExportStatus(String exportStatus) {
        this.exportStatus = exportStatus;
    }

    public String getInterfaceFile() {
        return interfaceFile;
    }

    public void setInterfaceFile(String interfaceFile) {
        this.interfaceFile = interfaceFile;
    }

    public String getControlFile() {
        return controlFile;
    }

    public void setControlFile(String controlFile) {
        this.controlFile = controlFile;
    }

    public LocalDate getExportTime() {
        return exportTime;
    }

    public void setExportTime(LocalDate exportTime) {
        this.exportTime = exportTime;
    }

    public Integer getEnqCode() {
        return enqCode;
    }

    public void setEnqCode(Integer enqCode) {
        this.enqCode = enqCode;
    }

    public LocalDate getTransTime() {
        return transTime;
    }

    public void setTransTime(LocalDate transTime) {
        this.transTime = transTime;
    }

    public String getCommChannel() {
        return commChannel;
    }

    public void setCommChannel(String commChannel) {
        this.commChannel = commChannel;
    }

    public String getCommParameter() {
        return commParameter;
    }

    public void setCommParameter(String commParameter) {
        this.commParameter = commParameter;
    }

    public String getIsNotSent() {
        return isNotSent;
    }

    public void setIsNotSent(String isNotSent) {
        this.isNotSent = isNotSent;
    }

    public String getIsRemSent() {
        return isRemSent;
    }

    public void setIsRemSent(String isRemSent) {
        this.isRemSent = isRemSent;
    }

    public String getVirtualInd() {
        return virtualInd;
    }

    public void setVirtualInd(String virtualInd) {
        this.virtualInd = virtualInd;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

        AppointmentTransDTO appointmentTransDTO = (AppointmentTransDTO) o;
        if(appointmentTransDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appointmentTransDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AppointmentTransDTO{" +
            "id=" + getId() +
            ", ern=" + getErn() +
            ", trn=" + getTrn() +
            ", preErn=" + getPreErn() +
            ", preTrn=" + getPreTrn() +
            ", appId=" + getAppId() +
            ", exportStatus='" + getExportStatus() + "'" +
            ", interfaceFile='" + getInterfaceFile() + "'" +
            ", controlFile='" + getControlFile() + "'" +
            ", exportTime='" + getExportTime() + "'" +
            ", enqCode=" + getEnqCode() +
            ", transTime='" + getTransTime() + "'" +
            ", commChannel='" + getCommChannel() + "'" +
            ", commParameter='" + getCommParameter() + "'" +
            ", isNotSent='" + getIsNotSent() + "'" +
            ", isRemSent='" + getIsRemSent() + "'" +
            ", virtualInd='" + getVirtualInd() + "'" +
            ", action='" + getAction() + "'" +
            ", active='" + getActive() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createBy='" + getCreateBy() + "'" +
            ", lastUpdBy='" + getLastUpdBy() + "'" +
            ", lastUpdDate='" + getLastUpdDate() + "'" +
            "}";
    }
}
