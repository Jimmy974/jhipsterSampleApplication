import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { AppointmentInfoComponent } from './appointment-info.component';
import { AppointmentInfoDetailComponent } from './appointment-info-detail.component';
import { AppointmentInfoPopupComponent } from './appointment-info-dialog.component';
import { AppointmentInfoDeletePopupComponent } from './appointment-info-delete-dialog.component';

export const appointmentInfoRoute: Routes = [
    {
        path: 'appointment-info',
        component: AppointmentInfoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AppointmentInfos'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'appointment-info/:id',
        component: AppointmentInfoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AppointmentInfos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const appointmentInfoPopupRoute: Routes = [
    {
        path: 'appointment-info-new',
        component: AppointmentInfoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AppointmentInfos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'appointment-info/:id/edit',
        component: AppointmentInfoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AppointmentInfos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'appointment-info/:id/delete',
        component: AppointmentInfoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AppointmentInfos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
