import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { QuotaPlan } from './quota-plan.model';
import { QuotaPlanService } from './quota-plan.service';

@Injectable()
export class QuotaPlanPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private quotaPlanService: QuotaPlanService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.quotaPlanService.find(id).subscribe((quotaPlan) => {
                    if (quotaPlan.apptDate) {
                        quotaPlan.apptDate = {
                            year: quotaPlan.apptDate.getFullYear(),
                            month: quotaPlan.apptDate.getMonth() + 1,
                            day: quotaPlan.apptDate.getDate()
                        };
                    }
                    if (quotaPlan.officeId) {
                        quotaPlan.officeId = {
                            year: quotaPlan.officeId.getFullYear(),
                            month: quotaPlan.officeId.getMonth() + 1,
                            day: quotaPlan.officeId.getDate()
                        };
                    }
                    if (quotaPlan.startTime) {
                        quotaPlan.startTime = {
                            year: quotaPlan.startTime.getFullYear(),
                            month: quotaPlan.startTime.getMonth() + 1,
                            day: quotaPlan.startTime.getDate()
                        };
                    }
                    if (quotaPlan.endTime) {
                        quotaPlan.endTime = {
                            year: quotaPlan.endTime.getFullYear(),
                            month: quotaPlan.endTime.getMonth() + 1,
                            day: quotaPlan.endTime.getDate()
                        };
                    }
                    if (quotaPlan.createDate) {
                        quotaPlan.createDate = {
                            year: quotaPlan.createDate.getFullYear(),
                            month: quotaPlan.createDate.getMonth() + 1,
                            day: quotaPlan.createDate.getDate()
                        };
                    }
                    if (quotaPlan.lastUpdDate) {
                        quotaPlan.lastUpdDate = {
                            year: quotaPlan.lastUpdDate.getFullYear(),
                            month: quotaPlan.lastUpdDate.getMonth() + 1,
                            day: quotaPlan.lastUpdDate.getDate()
                        };
                    }
                    this.ngbModalRef = this.quotaPlanModalRef(component, quotaPlan);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.quotaPlanModalRef(component, new QuotaPlan());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    quotaPlanModalRef(component: Component, quotaPlan: QuotaPlan): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.quotaPlan = quotaPlan;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
