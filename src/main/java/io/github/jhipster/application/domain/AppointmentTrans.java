package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A AppointmentTrans.
 */
@Entity
@Table(name = "appointment_trans")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AppointmentTrans implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ern", nullable = false)
    private Long ern;

    @NotNull
    @Column(name = "trn", nullable = false)
    private Long trn;

    @Column(name = "pre_ern")
    private Long preErn;

    @Column(name = "pre_trn")
    private Long preTrn;

    @NotNull
    @Column(name = "app_id", nullable = false)
    private Integer appId;

    @Column(name = "export_status")
    private String exportStatus;

    @Column(name = "interface_file")
    private String interfaceFile;

    @Column(name = "control_file")
    private String controlFile;

    @Column(name = "export_time")
    private LocalDate exportTime;

    @NotNull
    @Column(name = "enq_code", nullable = false)
    private Integer enqCode;

    @NotNull
    @Column(name = "trans_time", nullable = false)
    private LocalDate transTime;

    @NotNull
    @Column(name = "comm_channel", nullable = false)
    private String commChannel;

    @Column(name = "comm_parameter")
    private String commParameter;

    @NotNull
    @Column(name = "is_not_sent", nullable = false)
    private String isNotSent;

    @NotNull
    @Column(name = "is_rem_sent", nullable = false)
    private String isRemSent;

    @NotNull
    @Column(name = "virtual_ind", nullable = false)
    private String virtualInd;

    @NotNull
    @Column(name = "action", nullable = false)
    private String action;

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

    public Long getErn() {
        return ern;
    }

    public AppointmentTrans ern(Long ern) {
        this.ern = ern;
        return this;
    }

    public void setErn(Long ern) {
        this.ern = ern;
    }

    public Long getTrn() {
        return trn;
    }

    public AppointmentTrans trn(Long trn) {
        this.trn = trn;
        return this;
    }

    public void setTrn(Long trn) {
        this.trn = trn;
    }

    public Long getPreErn() {
        return preErn;
    }

    public AppointmentTrans preErn(Long preErn) {
        this.preErn = preErn;
        return this;
    }

    public void setPreErn(Long preErn) {
        this.preErn = preErn;
    }

    public Long getPreTrn() {
        return preTrn;
    }

    public AppointmentTrans preTrn(Long preTrn) {
        this.preTrn = preTrn;
        return this;
    }

    public void setPreTrn(Long preTrn) {
        this.preTrn = preTrn;
    }

    public Integer getAppId() {
        return appId;
    }

    public AppointmentTrans appId(Integer appId) {
        this.appId = appId;
        return this;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getExportStatus() {
        return exportStatus;
    }

    public AppointmentTrans exportStatus(String exportStatus) {
        this.exportStatus = exportStatus;
        return this;
    }

    public void setExportStatus(String exportStatus) {
        this.exportStatus = exportStatus;
    }

    public String getInterfaceFile() {
        return interfaceFile;
    }

    public AppointmentTrans interfaceFile(String interfaceFile) {
        this.interfaceFile = interfaceFile;
        return this;
    }

    public void setInterfaceFile(String interfaceFile) {
        this.interfaceFile = interfaceFile;
    }

    public String getControlFile() {
        return controlFile;
    }

    public AppointmentTrans controlFile(String controlFile) {
        this.controlFile = controlFile;
        return this;
    }

    public void setControlFile(String controlFile) {
        this.controlFile = controlFile;
    }

    public LocalDate getExportTime() {
        return exportTime;
    }

    public AppointmentTrans exportTime(LocalDate exportTime) {
        this.exportTime = exportTime;
        return this;
    }

    public void setExportTime(LocalDate exportTime) {
        this.exportTime = exportTime;
    }

    public Integer getEnqCode() {
        return enqCode;
    }

    public AppointmentTrans enqCode(Integer enqCode) {
        this.enqCode = enqCode;
        return this;
    }

    public void setEnqCode(Integer enqCode) {
        this.enqCode = enqCode;
    }

    public LocalDate getTransTime() {
        return transTime;
    }

    public AppointmentTrans transTime(LocalDate transTime) {
        this.transTime = transTime;
        return this;
    }

    public void setTransTime(LocalDate transTime) {
        this.transTime = transTime;
    }

    public String getCommChannel() {
        return commChannel;
    }

    public AppointmentTrans commChannel(String commChannel) {
        this.commChannel = commChannel;
        return this;
    }

    public void setCommChannel(String commChannel) {
        this.commChannel = commChannel;
    }

    public String getCommParameter() {
        return commParameter;
    }

    public AppointmentTrans commParameter(String commParameter) {
        this.commParameter = commParameter;
        return this;
    }

    public void setCommParameter(String commParameter) {
        this.commParameter = commParameter;
    }

    public String getIsNotSent() {
        return isNotSent;
    }

    public AppointmentTrans isNotSent(String isNotSent) {
        this.isNotSent = isNotSent;
        return this;
    }

    public void setIsNotSent(String isNotSent) {
        this.isNotSent = isNotSent;
    }

    public String getIsRemSent() {
        return isRemSent;
    }

    public AppointmentTrans isRemSent(String isRemSent) {
        this.isRemSent = isRemSent;
        return this;
    }

    public void setIsRemSent(String isRemSent) {
        this.isRemSent = isRemSent;
    }

    public String getVirtualInd() {
        return virtualInd;
    }

    public AppointmentTrans virtualInd(String virtualInd) {
        this.virtualInd = virtualInd;
        return this;
    }

    public void setVirtualInd(String virtualInd) {
        this.virtualInd = virtualInd;
    }

    public String getAction() {
        return action;
    }

    public AppointmentTrans action(String action) {
        this.action = action;
        return this;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActive() {
        return active;
    }

    public AppointmentTrans active(String active) {
        this.active = active;
        return this;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public AppointmentTrans createDate(LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public AppointmentTrans createBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDate getLastUpdBy() {
        return lastUpdBy;
    }

    public AppointmentTrans lastUpdBy(LocalDate lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
        return this;
    }

    public void setLastUpdBy(LocalDate lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
    }

    public String getLastUpdDate() {
        return lastUpdDate;
    }

    public AppointmentTrans lastUpdDate(String lastUpdDate) {
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
        AppointmentTrans appointmentTrans = (AppointmentTrans) o;
        if (appointmentTrans.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appointmentTrans.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AppointmentTrans{" +
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
