import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AppointmentTrans } from './appointment-trans.model';
import { AppointmentTransService } from './appointment-trans.service';

@Injectable()
export class AppointmentTransPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private appointmentTransService: AppointmentTransService

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
                this.appointmentTransService.find(id).subscribe((appointmentTrans) => {
                    if (appointmentTrans.exportTime) {
                        appointmentTrans.exportTime = {
                            year: appointmentTrans.exportTime.getFullYear(),
                            month: appointmentTrans.exportTime.getMonth() + 1,
                            day: appointmentTrans.exportTime.getDate()
                        };
                    }
                    if (appointmentTrans.transTime) {
                        appointmentTrans.transTime = {
                            year: appointmentTrans.transTime.getFullYear(),
                            month: appointmentTrans.transTime.getMonth() + 1,
                            day: appointmentTrans.transTime.getDate()
                        };
                    }
                    if (appointmentTrans.createDate) {
                        appointmentTrans.createDate = {
                            year: appointmentTrans.createDate.getFullYear(),
                            month: appointmentTrans.createDate.getMonth() + 1,
                            day: appointmentTrans.createDate.getDate()
                        };
                    }
                    if (appointmentTrans.lastUpdBy) {
                        appointmentTrans.lastUpdBy = {
                            year: appointmentTrans.lastUpdBy.getFullYear(),
                            month: appointmentTrans.lastUpdBy.getMonth() + 1,
                            day: appointmentTrans.lastUpdBy.getDate()
                        };
                    }
                    this.ngbModalRef = this.appointmentTransModalRef(component, appointmentTrans);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.appointmentTransModalRef(component, new AppointmentTrans());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    appointmentTransModalRef(component: Component, appointmentTrans: AppointmentTrans): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.appointmentTrans = appointmentTrans;
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
