package io.github.jhipster.application.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the QuotaPlan entity.
 */
public class QuotaPlanDTO implements Serializable {

    private Long id;

    @NotNull
    private String quotaId;

    @NotNull
    private String apptType;

    @NotNull
    private LocalDate apptDate;

    @NotNull
    private LocalDate officeId;

    @NotNull
    private Integer interval;

    @NotNull
    private LocalDate startTime;

    @NotNull
    private LocalDate endTime;

    @NotNull
    private Integer totalQuota;

    @NotNull
    private Integer avaQuota;

    @NotNull
    private Integer usedQuota;

    @NotNull
    private Integer freezeQuota;

    @NotNull
    private Integer virQuotaUsed;

    @NotNull
    private LocalDate createDate;

    @NotNull
    private String createBy;

    @NotNull
    private LocalDate lastUpdDate;

    @NotNull
    private String lastUpdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(String quotaId) {
        this.quotaId = quotaId;
    }

    public String getApptType() {
        return apptType;
    }

    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    public LocalDate getApptDate() {
        return apptDate;
    }

    public void setApptDate(LocalDate apptDate) {
        this.apptDate = apptDate;
    }

    public LocalDate getOfficeId() {
        return officeId;
    }

    public void setOfficeId(LocalDate officeId) {
        this.officeId = officeId;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalQuota() {
        return totalQuota;
    }

    public void setTotalQuota(Integer totalQuota) {
        this.totalQuota = totalQuota;
    }

    public Integer getAvaQuota() {
        return avaQuota;
    }

    public void setAvaQuota(Integer avaQuota) {
        this.avaQuota = avaQuota;
    }

    public Integer getUsedQuota() {
        return usedQuota;
    }

    public void setUsedQuota(Integer usedQuota) {
        this.usedQuota = usedQuota;
    }

    public Integer getFreezeQuota() {
        return freezeQuota;
    }

    public void setFreezeQuota(Integer freezeQuota) {
        this.freezeQuota = freezeQuota;
    }

    public Integer getVirQuotaUsed() {
        return virQuotaUsed;
    }

    public void setVirQuotaUsed(Integer virQuotaUsed) {
        this.virQuotaUsed = virQuotaUsed;
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

    public LocalDate getLastUpdDate() {
        return lastUpdDate;
    }

    public void setLastUpdDate(LocalDate lastUpdDate) {
        this.lastUpdDate = lastUpdDate;
    }

    public String getLastUpdBy() {
        return lastUpdBy;
    }

    public void setLastUpdBy(String lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuotaPlanDTO quotaPlanDTO = (QuotaPlanDTO) o;
        if(quotaPlanDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotaPlanDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotaPlanDTO{" +
            "id=" + getId() +
            ", quotaId='" + getQuotaId() + "'" +
            ", apptType='" + getApptType() + "'" +
            ", apptDate='" + getApptDate() + "'" +
            ", officeId='" + getOfficeId() + "'" +
            ", interval=" + getInterval() +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", totalQuota=" + getTotalQuota() +
            ", avaQuota=" + getAvaQuota() +
            ", usedQuota=" + getUsedQuota() +
            ", freezeQuota=" + getFreezeQuota() +
            ", virQuotaUsed=" + getVirQuotaUsed() +
            ", createDate='" + getCreateDate() + "'" +
            ", createBy='" + getCreateBy() + "'" +
            ", lastUpdDate='" + getLastUpdDate() + "'" +
            ", lastUpdBy='" + getLastUpdBy() + "'" +
            "}";
    }
}
