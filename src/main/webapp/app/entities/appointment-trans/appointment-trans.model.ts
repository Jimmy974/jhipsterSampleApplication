import { BaseEntity } from './../../shared';

export class AppointmentTrans implements BaseEntity {
    constructor(
        public id?: number,
        public ern?: number,
        public trn?: number,
        public preErn?: number,
        public preTrn?: number,
        public appId?: number,
        public exportStatus?: string,
        public interfaceFile?: string,
        public controlFile?: string,
        public exportTime?: any,
        public enqCode?: number,
        public transTime?: any,
        public commChannel?: string,
        public commParameter?: string,
        public isNotSent?: string,
        public isRemSent?: string,
        public virtualInd?: string,
        public action?: string,
        public active?: string,
        public createDate?: any,
        public createBy?: string,
        public lastUpdBy?: any,
        public lastUpdDate?: string,
    ) {
    }
}
