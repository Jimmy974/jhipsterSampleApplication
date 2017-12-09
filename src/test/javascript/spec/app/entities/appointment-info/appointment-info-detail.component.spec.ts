/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AppointmentInfoDetailComponent } from '../../../../../../main/webapp/app/entities/appointment-info/appointment-info-detail.component';
import { AppointmentInfoService } from '../../../../../../main/webapp/app/entities/appointment-info/appointment-info.service';
import { AppointmentInfo } from '../../../../../../main/webapp/app/entities/appointment-info/appointment-info.model';

describe('Component Tests', () => {

    describe('AppointmentInfo Management Detail Component', () => {
        let comp: AppointmentInfoDetailComponent;
        let fixture: ComponentFixture<AppointmentInfoDetailComponent>;
        let service: AppointmentInfoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AppointmentInfoDetailComponent],
                providers: [
                    AppointmentInfoService
                ]
            })
            .overrideTemplate(AppointmentInfoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AppointmentInfoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AppointmentInfoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new AppointmentInfo(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.appointmentInfo).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
