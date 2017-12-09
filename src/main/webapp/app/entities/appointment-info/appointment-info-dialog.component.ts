import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AppointmentInfo } from './appointment-info.model';
import { AppointmentInfoPopupService } from './appointment-info-popup.service';
import { AppointmentInfoService } from './appointment-info.service';

@Component({
    selector: 'jhi-appointment-info-dialog',
    templateUrl: './appointment-info-dialog.component.html'
})
export class AppointmentInfoDialogComponent implements OnInit {

    appointmentInfo: AppointmentInfo;
    isSaving: boolean;
    appDobDp: any;
    createDateDp: any;
    lastUpdByDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private appointmentInfoService: AppointmentInfoService,
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
        if (this.appointmentInfo.id !== undefined) {
            this.subscribeToSaveResponse(
                this.appointmentInfoService.update(this.appointmentInfo));
        } else {
            this.subscribeToSaveResponse(
                this.appointmentInfoService.create(this.appointmentInfo));
        }
    }

    private subscribeToSaveResponse(result: Observable<AppointmentInfo>) {
        result.subscribe((res: AppointmentInfo) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AppointmentInfo) {
        this.eventManager.broadcast({ name: 'appointmentInfoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-appointment-info-popup',
    template: ''
})
export class AppointmentInfoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private appointmentInfoPopupService: AppointmentInfoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.appointmentInfoPopupService
                    .open(AppointmentInfoDialogComponent as Component, params['id']);
            } else {
                this.appointmentInfoPopupService
                    .open(AppointmentInfoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
