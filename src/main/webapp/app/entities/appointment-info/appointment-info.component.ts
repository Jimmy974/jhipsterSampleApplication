import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AppointmentInfo } from './appointment-info.model';
import { AppointmentInfoService } from './appointment-info.service';
import { Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-appointment-info',
    templateUrl: './appointment-info.component.html'
})
export class AppointmentInfoComponent implements OnInit, OnDestroy {
appointmentInfos: AppointmentInfo[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private appointmentInfoService: AppointmentInfoService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.appointmentInfoService.query().subscribe(
            (res: ResponseWrapper) => {
                this.appointmentInfos = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInAppointmentInfos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: AppointmentInfo) {
        return item.id;
    }
    registerChangeInAppointmentInfos() {
        this.eventSubscriber = this.eventManager.subscribe('appointmentInfoListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
