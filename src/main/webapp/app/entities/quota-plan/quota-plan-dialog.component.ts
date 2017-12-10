import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { QuotaPlan } from './quota-plan.model';
import { QuotaPlanPopupService } from './quota-plan-popup.service';
import { QuotaPlanService } from './quota-plan.service';

@Component({
    selector: 'jhi-quota-plan-dialog',
    templateUrl: './quota-plan-dialog.component.html'
})
export class QuotaPlanDialogComponent implements OnInit {

    quotaPlan: QuotaPlan;
    isSaving: boolean;
    apptDateDp: any;
    officeIdDp: any;
    startTimeDp: any;
    endTimeDp: any;
    createDateDp: any;
    lastUpdDateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private quotaPlanService: QuotaPlanService,
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
        if (this.quotaPlan.id !== undefined) {
            this.subscribeToSaveResponse(
                this.quotaPlanService.update(this.quotaPlan));
        } else {
            this.subscribeToSaveResponse(
                this.quotaPlanService.create(this.quotaPlan));
        }
    }

    private subscribeToSaveResponse(result: Observable<QuotaPlan>) {
        result.subscribe((res: QuotaPlan) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: QuotaPlan) {
        this.eventManager.broadcast({ name: 'quotaPlanListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-quota-plan-popup',
    template: ''
})
export class QuotaPlanPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private quotaPlanPopupService: QuotaPlanPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.quotaPlanPopupService
                    .open(QuotaPlanDialogComponent as Component, params['id']);
            } else {
                this.quotaPlanPopupService
                    .open(QuotaPlanDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
