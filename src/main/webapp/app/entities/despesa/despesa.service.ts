import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDespesa } from 'app/shared/model/despesa.model';

type EntityResponseType = HttpResponse<IDespesa>;
type EntityArrayResponseType = HttpResponse<IDespesa[]>;

@Injectable({ providedIn: 'root' })
export class DespesaService {
  public resourceUrl = SERVER_API_URL + 'api/despesas';

  constructor(protected http: HttpClient) {}

  create(despesa: IDespesa): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(despesa);
    return this.http
      .post<IDespesa>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(despesa: IDespesa): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(despesa);
    return this.http
      .put<IDespesa>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDespesa>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDespesa[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(despesa: IDespesa): IDespesa {
    const copy: IDespesa = Object.assign({}, despesa, {
      dtDespesa: despesa.dtDespesa && despesa.dtDespesa.isValid() ? despesa.dtDespesa.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dtDespesa = res.body.dtDespesa ? moment(res.body.dtDespesa) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((despesa: IDespesa) => {
        despesa.dtDespesa = despesa.dtDespesa ? moment(despesa.dtDespesa) : undefined;
      });
    }
    return res;
  }
}
