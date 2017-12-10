/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';
import { Headers } from '@angular/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { QuotaPlanComponent } from '../../../../../../main/webapp/app/entities/quota-plan/quota-plan.component';
import { QuotaPlanService } from '../../../../../../main/webapp/app/entities/quota-plan/quota-plan.service';
import { QuotaPlan } from '../../../../../../main/webapp/app/entities/quota-plan/quota-plan.model';

describe('Component Tests', () => {

    describe('QuotaPlan Management Component', () => {
        let comp: QuotaPlanComponent;
        let fixture: ComponentFixture<QuotaPlanComponent>;
        let service: QuotaPlanService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [QuotaPlanComponent],
                providers: [
                    QuotaPlanService
                ]
            })
            .overrideTemplate(QuotaPlanComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(QuotaPlanComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(QuotaPlanService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new QuotaPlan(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.quotaPlans[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
