import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { QuotaPlanComponent } from './quota-plan.component';
import { QuotaPlanDetailComponent } from './quota-plan-detail.component';
import { QuotaPlanPopupComponent } from './quota-plan-dialog.component';
import { QuotaPlanDeletePopupComponent } from './quota-plan-delete-dialog.component';

export const quotaPlanRoute: Routes = [
    {
        path: 'quota-plan',
        component: QuotaPlanComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'QuotaPlans'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'quota-plan/:id',
        component: QuotaPlanDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'QuotaPlans'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const quotaPlanPopupRoute: Routes = [
    {
        path: 'quota-plan-new',
        component: QuotaPlanPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'QuotaPlans'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'quota-plan/:id/edit',
        component: QuotaPlanPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'QuotaPlans'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'quota-plan/:id/delete',
        component: QuotaPlanDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'QuotaPlans'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
