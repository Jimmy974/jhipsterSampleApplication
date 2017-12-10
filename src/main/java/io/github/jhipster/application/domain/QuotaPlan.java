package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A QuotaPlan.
 */
@Entity
@Table(name = "quota_plan")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QuotaPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "quota_id", nullable = false)
    private String quotaId;

    @NotNull
    @Column(name = "appt_type", nullable = false)
    private String apptType;

    @NotNull
    @Column(name = "appt_date", nullable = false)
    private LocalDate apptDate;

    @NotNull
    @Column(name = "office_id", nullable = false)
    private LocalDate officeId;

    @NotNull
    @Column(name = "jhi_interval", nullable = false)
    private Integer interval;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private LocalDate startTime;

    @NotNull
    @Column(name = "end_time", nullable = false)
    private LocalDate endTime;

    @NotNull
    @Column(name = "total_quota", nullable = false)
    private Integer totalQuota;

    @NotNull
    @Column(name = "ava_quota", nullable = false)
    private Integer avaQuota;

    @NotNull
    @Column(name = "used_quota", nullable = false)
    private Integer usedQuota;

    @NotNull
    @Column(name = "freeze_quota", nullable = false)
    private Integer freezeQuota;

    @NotNull
    @Column(name = "vir_quota_used", nullable = false)
    private Integer virQuotaUsed;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @NotNull
    @Column(name = "create_by", nullable = false)
    private String createBy;

    @NotNull
    @Column(name = "last_upd_date", nullable = false)
    private LocalDate lastUpdDate;

    @NotNull
    @Column(name = "last_upd_by", nullable = false)
    private String lastUpdBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuotaId() {
        return quotaId;
    }

    public QuotaPlan quotaId(String quotaId) {
        this.quotaId = quotaId;
        return this;
    }

    public void setQuotaId(String quotaId) {
        this.quotaId = quotaId;
    }

    public String getApptType() {
        return apptType;
    }

    public QuotaPlan apptType(String apptType) {
        this.apptType = apptType;
        return this;
    }

    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    public LocalDate getApptDate() {
        return apptDate;
    }

    public QuotaPlan apptDate(LocalDate apptDate) {
        this.apptDate = apptDate;
        return this;
    }

    public void setApptDate(LocalDate apptDate) {
        this.apptDate = apptDate;
    }

    public LocalDate getOfficeId() {
        return officeId;
    }

    public QuotaPlan officeId(LocalDate officeId) {
        this.officeId = officeId;
        return this;
    }

    public void setOfficeId(LocalDate officeId) {
        this.officeId = officeId;
    }

    public Integer getInterval() {
        return interval;
    }

    public QuotaPlan interval(Integer interval) {
        this.interval = interval;
        return this;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public QuotaPlan startTime(LocalDate startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public QuotaPlan endTime(LocalDate endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalQuota() {
        return totalQuota;
    }

    public QuotaPlan totalQuota(Integer totalQuota) {
        this.totalQuota = totalQuota;
        return this;
    }

    public void setTotalQuota(Integer totalQuota) {
        this.totalQuota = totalQuota;
    }

    public Integer getAvaQuota() {
        return avaQuota;
    }

    public QuotaPlan avaQuota(Integer avaQuota) {
        this.avaQuota = avaQuota;
        return this;
    }

    public void setAvaQuota(Integer avaQuota) {
        this.avaQuota = avaQuota;
    }

    public Integer getUsedQuota() {
        return usedQuota;
    }

    public QuotaPlan usedQuota(Integer usedQuota) {
        this.usedQuota = usedQuota;
        return this;
    }

    public void setUsedQuota(Integer usedQuota) {
        this.usedQuota = usedQuota;
    }

    public Integer getFreezeQuota() {
        return freezeQuota;
    }

    public QuotaPlan freezeQuota(Integer freezeQuota) {
        this.freezeQuota = freezeQuota;
        return this;
    }

    public void setFreezeQuota(Integer freezeQuota) {
        this.freezeQuota = freezeQuota;
    }

    public Integer getVirQuotaUsed() {
        return virQuotaUsed;
    }

    public QuotaPlan virQuotaUsed(Integer virQuotaUsed) {
        this.virQuotaUsed = virQuotaUsed;
        return this;
    }

    public void setVirQuotaUsed(Integer virQuotaUsed) {
        this.virQuotaUsed = virQuotaUsed;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public QuotaPlan createDate(LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public QuotaPlan createBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDate getLastUpdDate() {
        return lastUpdDate;
    }

    public QuotaPlan lastUpdDate(LocalDate lastUpdDate) {
        this.lastUpdDate = lastUpdDate;
        return this;
    }

    public void setLastUpdDate(LocalDate lastUpdDate) {
        this.lastUpdDate = lastUpdDate;
    }

    public String getLastUpdBy() {
        return lastUpdBy;
    }

    public QuotaPlan lastUpdBy(String lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
        return this;
    }

    public void setLastUpdBy(String lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
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
        QuotaPlan quotaPlan = (QuotaPlan) o;
        if (quotaPlan.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotaPlan.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotaPlan{" +
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
