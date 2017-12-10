import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AppointmentTrans } from './appointment-trans.model';
import { AppointmentTransPopupService } from './appointment-trans-popup.service';
import { AppointmentTransService } from './appointment-trans.service';

@Component({
    selector: 'jhi-appointment-trans-dialog',
    templateUrl: './appointment-trans-dialog.component.html'
})
export class AppointmentTransDialogComponent implements OnInit {

    appointmentTrans: AppointmentTrans;
    isSaving: boolean;
    exportTimeDp: any;
    transTimeDp: any;
    createDateDp: any;
    lastUpdByDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private appointmentTransService: AppointmentTransService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.appointmentTrans.id !== undefined) {
            this.subscribeToSaveResponse(
                this.appointmentTransService.update(this.appointmentTrans));
        } else {
            this.subscribeToSaveResponse(
                this.appointmentTransService.create(this.appointmentTrans));
        }
    }

    private subscribeToSaveResponse(result: Observable<AppointmentTrans>) {
        result.subscribe((res: AppointmentTrans) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AppointmentTrans) {
        this.eventManager.broadcast({ name: 'appointmentTransListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-appointment-trans-popup',
    template: ''
})
export class AppointmentTransPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private appointmentTransPopupService: AppointmentTransPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.appointmentTransPopupService
                    .open(AppointmentTransDialogComponent as Component, params['id']);
            } else {
                this.appointmentTransPopupService
                    .open(AppointmentTransDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
