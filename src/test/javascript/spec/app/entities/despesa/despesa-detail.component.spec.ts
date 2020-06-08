import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EdicarTestModule } from '../../../test.module';
import { DespesaDetailComponent } from 'app/entities/despesa/despesa-detail.component';
import { Despesa } from 'app/shared/model/despesa.model';

describe('Component Tests', () => {
  describe('Despesa Management Detail Component', () => {
    let comp: DespesaDetailComponent;
    let fixture: ComponentFixture<DespesaDetailComponent>;
    const route = ({ data: of({ despesa: new Despesa(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EdicarTestModule],
        declarations: [DespesaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DespesaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DespesaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load despesa on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.despesa).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
