import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { AppointmentTransComponent } from './appointment-trans.component';
import { AppointmentTransDetailComponent } from './appointment-trans-detail.component';
import { AppointmentTransPopupComponent } from './appointment-trans-dialog.component';
import { AppointmentTransDeletePopupComponent } from './appointment-trans-delete-dialog.component';

export const appointmentTransRoute: Routes = [
    {
        path: 'appointment-trans',
        component: AppointmentTransComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AppointmentTrans'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'appointment-trans/:id',
        component: AppointmentTransDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AppointmentTrans'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const appointmentTransPopupRoute: Routes = [
    {
        path: 'appointment-trans-new',
        component: AppointmentTransPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AppointmentTrans'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'appointment-trans/:id/edit',
        component: AppointmentTransPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AppointmentTrans'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'appointment-trans/:id/delete',
        component: AppointmentTransDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AppointmentTrans'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
