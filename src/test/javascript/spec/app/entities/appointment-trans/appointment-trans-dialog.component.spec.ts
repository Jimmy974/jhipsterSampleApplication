/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AppointmentTransDialogComponent } from '../../../../../../main/webapp/app/entities/appointment-trans/appointment-trans-dialog.component';
import { AppointmentTransService } from '../../../../../../main/webapp/app/entities/appointment-trans/appointment-trans.service';
import { AppointmentTrans } from '../../../../../../main/webapp/app/entities/appointment-trans/appointment-trans.model';

describe('Component Tests', () => {

    describe('AppointmentTrans Management Dialog Component', () => {
        let comp: AppointmentTransDialogComponent;
        let fixture: ComponentFixture<AppointmentTransDialogComponent>;
        let service: AppointmentTransService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AppointmentTransDialogComponent],
                providers: [
                    AppointmentTransService
                ]
            })
            .overrideTemplate(AppointmentTransDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AppointmentTransDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AppointmentTransService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new AppointmentTrans(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.appointmentTrans = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'appointmentTransListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new AppointmentTrans();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.appointmentTrans = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'appointmentTransListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
