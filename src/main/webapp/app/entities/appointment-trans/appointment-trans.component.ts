import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AppointmentTrans } from './appointment-trans.model';
import { AppointmentTransService } from './appointment-trans.service';
import { Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-appointment-trans',
    templateUrl: './appointment-trans.component.html'
})
export class AppointmentTransComponent implements OnInit, OnDestroy {
appointmentTrans: AppointmentTrans[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private appointmentTransService: AppointmentTransService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.appointmentTransService.query().subscribe(
            (res: ResponseWrapper) => {
                this.appointmentTrans = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInAppointmentTrans();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: AppointmentTrans) {
        return item.id;
    }
    registerChangeInAppointmentTrans() {
        this.eventSubscriber = this.eventManager.subscribe('appointmentTransListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
