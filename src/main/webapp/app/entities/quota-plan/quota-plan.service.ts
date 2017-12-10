import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { QuotaPlan } from './quota-plan.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class QuotaPlanService {

    private resourceUrl = SERVER_API_URL + 'api/quota-plans';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(quotaPlan: QuotaPlan): Observable<QuotaPlan> {
        const copy = this.convert(quotaPlan);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(quotaPlan: QuotaPlan): Observable<QuotaPlan> {
        const copy = this.convert(quotaPlan);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<QuotaPlan> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to QuotaPlan.
     */
    private convertItemFromServer(json: any): QuotaPlan {
        const entity: QuotaPlan = Object.assign(new QuotaPlan(), json);
        entity.apptDate = this.dateUtils
            .convertLocalDateFromServer(json.apptDate);
        entity.officeId = this.dateUtils
            .convertLocalDateFromServer(json.officeId);
        entity.startTime = this.dateUtils
            .convertLocalDateFromServer(json.startTime);
        entity.endTime = this.dateUtils
            .convertLocalDateFromServer(json.endTime);
        entity.createDate = this.dateUtils
            .convertLocalDateFromServer(json.createDate);
        entity.lastUpdDate = this.dateUtils
            .convertLocalDateFromServer(json.lastUpdDate);
        return entity;
    }

    /**
     * Convert a QuotaPlan to a JSON which can be sent to the server.
     */
    private convert(quotaPlan: QuotaPlan): QuotaPlan {
        const copy: QuotaPlan = Object.assign({}, quotaPlan);
        copy.apptDate = this.dateUtils
            .convertLocalDateToServer(quotaPlan.apptDate);
        copy.officeId = this.dateUtils
            .convertLocalDateToServer(quotaPlan.officeId);
        copy.startTime = this.dateUtils
            .convertLocalDateToServer(quotaPlan.startTime);
        copy.endTime = this.dateUtils
            .convertLocalDateToServer(quotaPlan.endTime);
        copy.createDate = this.dateUtils
            .convertLocalDateToServer(quotaPlan.createDate);
        copy.lastUpdDate = this.dateUtils
            .convertLocalDateToServer(quotaPlan.lastUpdDate);
        return copy;
    }
}
