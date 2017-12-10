import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { QuotaPlan } from './quota-plan.model';
import { QuotaPlanService } from './quota-plan.service';

@Component({
    selector: 'jhi-quota-plan-detail',
    templateUrl: './quota-plan-detail.component.html'
})
export class QuotaPlanDetailComponent implements OnInit, OnDestroy {

    quotaPlan: QuotaPlan;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private quotaPlanService: QuotaPlanService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInQuotaPlans();
    }

    load(id) {
        this.quotaPlanService.find(id).subscribe((quotaPlan) => {
            this.quotaPlan = quotaPlan;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInQuotaPlans() {
        this.eventSubscriber = this.eventManager.subscribe(
            'quotaPlanListModification',
            (response) => this.load(this.quotaPlan.id)
        );
    }
}
