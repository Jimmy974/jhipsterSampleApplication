import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { QuotaPlan } from './quota-plan.model';
import { QuotaPlanService } from './quota-plan.service';
import { Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-quota-plan',
    templateUrl: './quota-plan.component.html'
})
export class QuotaPlanComponent implements OnInit, OnDestroy {
quotaPlans: QuotaPlan[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private quotaPlanService: QuotaPlanService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.quotaPlanService.query().subscribe(
            (res: ResponseWrapper) => {
                this.quotaPlans = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInQuotaPlans();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: QuotaPlan) {
        return item.id;
    }
    registerChangeInQuotaPlans() {
        this.eventSubscriber = this.eventManager.subscribe('quotaPlanListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
