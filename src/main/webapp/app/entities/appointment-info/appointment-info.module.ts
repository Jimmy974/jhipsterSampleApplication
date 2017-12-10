import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    AppointmentInfoService,
    AppointmentInfoPopupService,
    AppointmentInfoComponent,
    AppointmentInfoDetailComponent,
    AppointmentInfoDialogComponent,
    AppointmentInfoPopupComponent,
    AppointmentInfoDeletePopupComponent,
    AppointmentInfoDeleteDialogComponent,
    appointmentInfoRoute,
    appointmentInfoPopupRoute,
} from './';

const ENTITY_STATES = [
    ...appointmentInfoRoute,
    ...appointmentInfoPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        AppointmentInfoComponent,
        AppointmentInfoDetailComponent,
        AppointmentInfoDialogComponent,
        AppointmentInfoDeleteDialogComponent,
        AppointmentInfoPopupComponent,
        AppointmentInfoDeletePopupComponent,
    ],
    entryComponents: [
        AppointmentInfoComponent,
        AppointmentInfoDialogComponent,
        AppointmentInfoPopupComponent,
        AppointmentInfoDeleteDialogComponent,
        AppointmentInfoDeletePopupComponent,
    ],
    providers: [
        AppointmentInfoService,
        AppointmentInfoPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationAppointmentInfoModule {}
