import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AppointmentInfo } from './appointment-info.model';
import { AppointmentInfoPopupService } from './appointment-info-popup.service';
import { AppointmentInfoService } from './appointment-info.service';

@Component({
    selector: 'jhi-appointment-info-delete-dialog',
    templateUrl: './appointment-info-delete-dialog.component.html'
})
export class AppointmentInfoDeleteDialogComponent {

    appointmentInfo: AppointmentInfo;

    constructor(
        private appointmentInfoService: AppointmentInfoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.appointmentInfoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'appointmentInfoListModification',
                content: 'Deleted an appointmentInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-appointment-info-delete-popup',
    template: ''
})
export class AppointmentInfoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private appointmentInfoPopupService: AppointmentInfoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.appointmentInfoPopupService
                .open(AppointmentInfoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
