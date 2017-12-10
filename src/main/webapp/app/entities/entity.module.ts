import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterSampleApplicationAppointmentInfoModule } from './appointment-info/appointment-info.module';
import { JhipsterSampleApplicationAppointmentTransModule } from './appointment-trans/appointment-trans.module';
import { JhipsterSampleApplicationQuotaPlanModule } from './quota-plan/quota-plan.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        JhipsterSampleApplicationAppointmentInfoModule,
        JhipsterSampleApplicationAppointmentTransModule,
        JhipsterSampleApplicationQuotaPlanModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationEntityModule {}
