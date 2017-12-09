import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { AppointmentInfo } from './appointment-info.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AppointmentInfoService {

    private resourceUrl = SERVER_API_URL + 'api/appointment-infos';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(appointmentInfo: AppointmentInfo): Observable<AppointmentInfo> {
        const copy = this.convert(appointmentInfo);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(appointmentInfo: AppointmentInfo): Observable<AppointmentInfo> {
        const copy = this.convert(appointmentInfo);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<AppointmentInfo> {
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
     * Convert a returned JSON object to AppointmentInfo.
     */
    private convertItemFromServer(json: any): AppointmentInfo {
        const entity: AppointmentInfo = Object.assign(new AppointmentInfo(), json);
        entity.appDob = this.dateUtils
            .convertLocalDateFromServer(json.appDob);
        entity.createDate = this.dateUtils
            .convertLocalDateFromServer(json.createDate);
        entity.lastUpdBy = this.dateUtils
            .convertLocalDateFromServer(json.lastUpdBy);
        return entity;
    }

    /**
     * Convert a AppointmentInfo to a JSON which can be sent to the server.
     */
    private convert(appointmentInfo: AppointmentInfo): AppointmentInfo {
        const copy: AppointmentInfo = Object.assign({}, appointmentInfo);
        copy.appDob = this.dateUtils
            .convertLocalDateToServer(appointmentInfo.appDob);
        copy.createDate = this.dateUtils
            .convertLocalDateToServer(appointmentInfo.createDate);
        copy.lastUpdBy = this.dateUtils
            .convertLocalDateToServer(appointmentInfo.lastUpdBy);
        return copy;
    }
}
