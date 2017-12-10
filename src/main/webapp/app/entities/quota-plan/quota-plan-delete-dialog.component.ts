import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { QuotaPlan } from './quota-plan.model';
import { QuotaPlanPopupService } from './quota-plan-popup.service';
import { QuotaPlanService } from './quota-plan.service';

@Component({
    selector: 'jhi-quota-plan-delete-dialog',
    templateUrl: './quota-plan-delete-dialog.component.html'
})
export class QuotaPlanDeleteDialogComponent {

    quotaPlan: QuotaPlan;

    constructor(
        private quotaPlanService: QuotaPlanService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.quotaPlanService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'quotaPlanListModification',
                content: 'Deleted an quotaPlan'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quota-plan-delete-popup',
    template: ''
})
export class QuotaPlanDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private quotaPlanPopupService: QuotaPlanPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.quotaPlanPopupService
                .open(QuotaPlanDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
