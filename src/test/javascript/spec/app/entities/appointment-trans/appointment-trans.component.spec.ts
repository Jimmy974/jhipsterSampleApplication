/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';
import { Headers } from '@angular/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AppointmentTransComponent } from '../../../../../../main/webapp/app/entities/appointment-trans/appointment-trans.component';
import { AppointmentTransService } from '../../../../../../main/webapp/app/entities/appointment-trans/appointment-trans.service';
import { AppointmentTrans } from '../../../../../../main/webapp/app/entities/appointment-trans/appointment-trans.model';

describe('Component Tests', () => {

    describe('AppointmentTrans Management Component', () => {
        let comp: AppointmentTransComponent;
        let fixture: ComponentFixture<AppointmentTransComponent>;
        let service: AppointmentTransService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AppointmentTransComponent],
                providers: [
                    AppointmentTransService
                ]
            })
            .overrideTemplate(AppointmentTransComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AppointmentTransComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AppointmentTransService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new AppointmentTrans(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.appointmentTrans[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
