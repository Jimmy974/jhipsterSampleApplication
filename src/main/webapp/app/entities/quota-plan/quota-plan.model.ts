import { BaseEntity } from './../../shared';

export class QuotaPlan implements BaseEntity {
    constructor(
        public id?: number,
        public quotaId?: string,
        public apptType?: string,
        public apptDate?: any,
        public officeId?: any,
        public interval?: number,
        public startTime?: any,
        public endTime?: any,
        public totalQuota?: number,
        public avaQuota?: number,
        public usedQuota?: number,
        public freezeQuota?: number,
        public virQuotaUsed?: number,
        public createDate?: any,
        public createBy?: string,
        public lastUpdDate?: any,
        public lastUpdBy?: string,
    ) {
    }
}
