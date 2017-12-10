import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AppointmentTrans } from './appointment-trans.model';
import { AppointmentTransService } from './appointment-trans.service';

@Component({
    selector: 'jhi-appointment-trans-detail',
    templateUrl: './appointment-trans-detail.component.html'
})
export class AppointmentTransDetailComponent implements OnInit, OnDestroy {

    appointmentTrans: AppointmentTrans;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private appointmentTransService: AppointmentTransService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAppointmentTrans();
    }

    load(id) {
        this.appointmentTransService.find(id).subscribe((appointmentTrans) => {
            this.appointmentTrans = appointmentTrans;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAppointmentTrans() {
        this.eventSubscriber = this.eventManager.subscribe(
            'appointmentTransListModification',
            (response) => this.load(this.appointmentTrans.id)
        );
    }
}
