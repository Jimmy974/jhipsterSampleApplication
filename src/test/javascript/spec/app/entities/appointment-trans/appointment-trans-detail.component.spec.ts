/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AppointmentTransDetailComponent } from '../../../../../../main/webapp/app/entities/appointment-trans/appointment-trans-detail.component';
import { AppointmentTransService } from '../../../../../../main/webapp/app/entities/appointment-trans/appointment-trans.service';
import { AppointmentTrans } from '../../../../../../main/webapp/app/entities/appointment-trans/appointment-trans.model';

describe('Component Tests', () => {

    describe('AppointmentTrans Management Detail Component', () => {
        let comp: AppointmentTransDetailComponent;
        let fixture: ComponentFixture<AppointmentTransDetailComponent>;
        let service: AppointmentTransService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AppointmentTransDetailComponent],
                providers: [
                    AppointmentTransService
                ]
            })
            .overrideTemplate(AppointmentTransDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AppointmentTransDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AppointmentTransService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new AppointmentTrans(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.appointmentTrans).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
