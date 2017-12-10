import { BaseEntity } from './../../shared';

export class AppointmentInfo implements BaseEntity {
    constructor(
        public id?: number,
        public trn?: number,
        public groupMemId?: string,
        public quotaId?: string,
        public serviceType?: string,
        public apmidType?: string,
        public apmidCode?: string,
        public appDob?: any,
        public servicesNature?: string,
        public ageInd?: string,
        public prefilInd?: string,
        public active?: string,
        public createDate?: any,
        public createBy?: string,
        public lastUpdBy?: any,
        public lastUpdDate?: string,
    ) {
    }
}
