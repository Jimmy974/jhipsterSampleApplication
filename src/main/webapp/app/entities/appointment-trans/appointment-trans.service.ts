import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { AppointmentTrans } from './appointment-trans.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AppointmentTransService {

    private resourceUrl = SERVER_API_URL + 'api/appointment-trans';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(appointmentTrans: AppointmentTrans): Observable<AppointmentTrans> {
        const copy = this.convert(appointmentTrans);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(appointmentTrans: AppointmentTrans): Observable<AppointmentTrans> {
        const copy = this.convert(appointmentTrans);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<AppointmentTrans> {
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
     * Convert a returned JSON object to AppointmentTrans.
     */
    private convertItemFromServer(json: any): AppointmentTrans {
        const entity: AppointmentTrans = Object.assign(new AppointmentTrans(), json);
        entity.exportTime = this.dateUtils
            .convertLocalDateFromServer(json.exportTime);
        entity.transTime = this.dateUtils
            .convertLocalDateFromServer(json.transTime);
        entity.createDate = this.dateUtils
            .convertLocalDateFromServer(json.createDate);
        entity.lastUpdBy = this.dateUtils
            .convertLocalDateFromServer(json.lastUpdBy);
        return entity;
    }

    /**
     * Convert a AppointmentTrans to a JSON which can be sent to the server.
     */
    private convert(appointmentTrans: AppointmentTrans): AppointmentTrans {
        const copy: AppointmentTrans = Object.assign({}, appointmentTrans);
        copy.exportTime = this.dateUtils
            .convertLocalDateToServer(appointmentTrans.exportTime);
        copy.transTime = this.dateUtils
            .convertLocalDateToServer(appointmentTrans.transTime);
        copy.createDate = this.dateUtils
            .convertLocalDateToServer(appointmentTrans.createDate);
        copy.lastUpdBy = this.dateUtils
            .convertLocalDateToServer(appointmentTrans.lastUpdBy);
        return copy;
    }
}
