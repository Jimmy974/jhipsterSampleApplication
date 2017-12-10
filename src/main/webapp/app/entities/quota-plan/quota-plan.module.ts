import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    QuotaPlanService,
    QuotaPlanPopupService,
    QuotaPlanComponent,
    QuotaPlanDetailComponent,
    QuotaPlanDialogComponent,
    QuotaPlanPopupComponent,
    QuotaPlanDeletePopupComponent,
    QuotaPlanDeleteDialogComponent,
    quotaPlanRoute,
    quotaPlanPopupRoute,
} from './';

const ENTITY_STATES = [
    ...quotaPlanRoute,
    ...quotaPlanPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        QuotaPlanComponent,
        QuotaPlanDetailComponent,
        QuotaPlanDialogComponent,
        QuotaPlanDeleteDialogComponent,
        QuotaPlanPopupComponent,
        QuotaPlanDeletePopupComponent,
    ],
    entryComponents: [
        QuotaPlanComponent,
        QuotaPlanDialogComponent,
        QuotaPlanPopupComponent,
        QuotaPlanDeleteDialogComponent,
        QuotaPlanDeletePopupComponent,
    ],
    providers: [
        QuotaPlanService,
        QuotaPlanPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationQuotaPlanModule {}
