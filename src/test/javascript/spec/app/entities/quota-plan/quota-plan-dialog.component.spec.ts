/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { QuotaPlanDialogComponent } from '../../../../../../main/webapp/app/entities/quota-plan/quota-plan-dialog.component';
import { QuotaPlanService } from '../../../../../../main/webapp/app/entities/quota-plan/quota-plan.service';
import { QuotaPlan } from '../../../../../../main/webapp/app/entities/quota-plan/quota-plan.model';

describe('Component Tests', () => {

    describe('QuotaPlan Management Dialog Component', () => {
        let comp: QuotaPlanDialogComponent;
        let fixture: ComponentFixture<QuotaPlanDialogComponent>;
        let service: QuotaPlanService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [QuotaPlanDialogComponent],
                providers: [
                    QuotaPlanService
                ]
            })
            .overrideTemplate(QuotaPlanDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(QuotaPlanDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(QuotaPlanService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new QuotaPlan(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.quotaPlan = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'quotaPlanListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new QuotaPlan();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.quotaPlan = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'quotaPlanListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
