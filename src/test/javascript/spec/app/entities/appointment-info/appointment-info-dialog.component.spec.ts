/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AppointmentInfoDialogComponent } from '../../../../../../main/webapp/app/entities/appointment-info/appointment-info-dialog.component';
import { AppointmentInfoService } from '../../../../../../main/webapp/app/entities/appointment-info/appointment-info.service';
import { AppointmentInfo } from '../../../../../../main/webapp/app/entities/appointment-info/appointment-info.model';

describe('Component Tests', () => {

    describe('AppointmentInfo Management Dialog Component', () => {
        let comp: AppointmentInfoDialogComponent;
        let fixture: ComponentFixture<AppointmentInfoDialogComponent>;
        let service: AppointmentInfoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AppointmentInfoDialogComponent],
                providers: [
                    AppointmentInfoService
                ]
            })
            .overrideTemplate(AppointmentInfoDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AppointmentInfoDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AppointmentInfoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new AppointmentInfo(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.appointmentInfo = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'appointmentInfoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new AppointmentInfo();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.appointmentInfo = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'appointmentInfoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
