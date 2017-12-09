/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';
import { Headers } from '@angular/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AppointmentInfoComponent } from '../../../../../../main/webapp/app/entities/appointment-info/appointment-info.component';
import { AppointmentInfoService } from '../../../../../../main/webapp/app/entities/appointment-info/appointment-info.service';
import { AppointmentInfo } from '../../../../../../main/webapp/app/entities/appointment-info/appointment-info.model';

describe('Component Tests', () => {

    describe('AppointmentInfo Management Component', () => {
        let comp: AppointmentInfoComponent;
        let fixture: ComponentFixture<AppointmentInfoComponent>;
        let service: AppointmentInfoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AppointmentInfoComponent],
                providers: [
                    AppointmentInfoService
                ]
            })
            .overrideTemplate(AppointmentInfoComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AppointmentInfoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AppointmentInfoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new AppointmentInfo(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.appointmentInfos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
