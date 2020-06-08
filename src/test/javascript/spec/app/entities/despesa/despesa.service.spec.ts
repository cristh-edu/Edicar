import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { DespesaService } from 'app/entities/despesa/despesa.service';
import { IDespesa, Despesa } from 'app/shared/model/despesa.model';

describe('Service Tests', () => {
  describe('Despesa Service', () => {
    let injector: TestBed;
    let service: DespesaService;
    let httpMock: HttpTestingController;
    let elemDefault: IDespesa;
    let expectedResult: IDespesa | IDespesa[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DespesaService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Despesa(0, currentDate, 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dtDespesa: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Despesa', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dtDespesa: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dtDespesa: currentDate,
          },
          returnedFromService
        );

        service.create(new Despesa()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Despesa', () => {
        const returnedFromService = Object.assign(
          {
            dtDespesa: currentDate.format(DATE_FORMAT),
            nmDespesa: 'BBBBBB',
            valor: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dtDespesa: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Despesa', () => {
        const returnedFromService = Object.assign(
          {
            dtDespesa: currentDate.format(DATE_FORMAT),
            nmDespesa: 'BBBBBB',
            valor: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dtDespesa: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Despesa', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
