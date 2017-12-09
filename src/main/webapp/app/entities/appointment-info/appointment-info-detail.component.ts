import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AppointmentInfo } from './appointment-info.model';
import { AppointmentInfoService } from './appointment-info.service';

@Component({
    selector: 'jhi-appointment-info-detail',
    templateUrl: './appointment-info-detail.component.html'
})
export class AppointmentInfoDetailComponent implements OnInit, OnDestroy {

    appointmentInfo: AppointmentInfo;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private appointmentInfoService: AppointmentInfoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAppointmentInfos();
    }

    load(id) {
        this.appointmentInfoService.find(id).subscribe((appointmentInfo) => {
            this.appointmentInfo = appointmentInfo;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAppointmentInfos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'appointmentInfoListModification',
            (response) => this.load(this.appointmentInfo.id)
        );
    }
}
