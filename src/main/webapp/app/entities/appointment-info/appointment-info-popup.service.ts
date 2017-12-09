import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AppointmentInfo } from './appointment-info.model';
import { AppointmentInfoService } from './appointment-info.service';

@Injectable()
export class AppointmentInfoPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private appointmentInfoService: AppointmentInfoService

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
                this.appointmentInfoService.find(id).subscribe((appointmentInfo) => {
                    if (appointmentInfo.appDob) {
                        appointmentInfo.appDob = {
                            year: appointmentInfo.appDob.getFullYear(),
                            month: appointmentInfo.appDob.getMonth() + 1,
                            day: appointmentInfo.appDob.getDate()
                        };
                    }
                    if (appointmentInfo.createDate) {
                        appointmentInfo.createDate = {
                            year: appointmentInfo.createDate.getFullYear(),
                            month: appointmentInfo.createDate.getMonth() + 1,
                            day: appointmentInfo.createDate.getDate()
                        };
                    }
                    if (appointmentInfo.lastUpdBy) {
                        appointmentInfo.lastUpdBy = {
                            year: appointmentInfo.lastUpdBy.getFullYear(),
                            month: appointmentInfo.lastUpdBy.getMonth() + 1,
                            day: appointmentInfo.lastUpdBy.getDate()
                        };
                    }
                    this.ngbModalRef = this.appointmentInfoModalRef(component, appointmentInfo);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.appointmentInfoModalRef(component, new AppointmentInfo());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    appointmentInfoModalRef(component: Component, appointmentInfo: AppointmentInfo): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.appointmentInfo = appointmentInfo;
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
