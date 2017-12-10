import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    AppointmentTransService,
    AppointmentTransPopupService,
    AppointmentTransComponent,
    AppointmentTransDetailComponent,
    AppointmentTransDialogComponent,
    AppointmentTransPopupComponent,
    AppointmentTransDeletePopupComponent,
    AppointmentTransDeleteDialogComponent,
    appointmentTransRoute,
    appointmentTransPopupRoute,
} from './';

const ENTITY_STATES = [
    ...appointmentTransRoute,
    ...appointmentTransPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        AppointmentTransComponent,
        AppointmentTransDetailComponent,
        AppointmentTransDialogComponent,
        AppointmentTransDeleteDialogComponent,
        AppointmentTransPopupComponent,
        AppointmentTransDeletePopupComponent,
    ],
    entryComponents: [
        AppointmentTransComponent,
        AppointmentTransDialogComponent,
        AppointmentTransPopupComponent,
        AppointmentTransDeleteDialogComponent,
        AppointmentTransDeletePopupComponent,
    ],
    providers: [
        AppointmentTransService,
        AppointmentTransPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationAppointmentTransModule {}
