/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { QuotaPlanDetailComponent } from '../../../../../../main/webapp/app/entities/quota-plan/quota-plan-detail.component';
import { QuotaPlanService } from '../../../../../../main/webapp/app/entities/quota-plan/quota-plan.service';
import { QuotaPlan } from '../../../../../../main/webapp/app/entities/quota-plan/quota-plan.model';

describe('Component Tests', () => {

    describe('QuotaPlan Management Detail Component', () => {
        let comp: QuotaPlanDetailComponent;
        let fixture: ComponentFixture<QuotaPlanDetailComponent>;
        let service: QuotaPlanService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [QuotaPlanDetailComponent],
                providers: [
                    QuotaPlanService
                ]
            })
            .overrideTemplate(QuotaPlanDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(QuotaPlanDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(QuotaPlanService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new QuotaPlan(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.quotaPlan).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
