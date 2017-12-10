import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AppointmentTrans } from './appointment-trans.model';
import { AppointmentTransPopupService } from './appointment-trans-popup.service';
import { AppointmentTransService } from './appointment-trans.service';

@Component({
    selector: 'jhi-appointment-trans-delete-dialog',
    templateUrl: './appointment-trans-delete-dialog.component.html'
})
export class AppointmentTransDeleteDialogComponent {

    appointmentTrans: AppointmentTrans;

    constructor(
        private appointmentTransService: AppointmentTransService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.appointmentTransService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'appointmentTransListModification',
                content: 'Deleted an appointmentTrans'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-appointment-trans-delete-popup',
    template: ''
})
export class AppointmentTransDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private appointmentTransPopupService: AppointmentTransPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.appointmentTransPopupService
                .open(AppointmentTransDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
