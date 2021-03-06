import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDespesa, Despesa } from 'app/shared/model/despesa.model';
import { DespesaService } from './despesa.service';
import { DespesaComponent } from './despesa.component';
import { DespesaDetailComponent } from './despesa-detail.component';
import { DespesaUpdateComponent } from './despesa-update.component';

@Injectable({ providedIn: 'root' })
export class DespesaResolve implements Resolve<IDespesa> {
  constructor(private service: DespesaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDespesa> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((despesa: HttpResponse<Despesa>) => {
          if (despesa.body) {
            return of(despesa.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Despesa());
  }
}

export const despesaRoute: Routes = [
  {
    path: '',
    component: DespesaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'edicarApp.despesa.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DespesaDetailComponent,
    resolve: {
      despesa: DespesaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'edicarApp.despesa.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DespesaUpdateComponent,
    resolve: {
      despesa: DespesaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'edicarApp.despesa.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DespesaUpdateComponent,
    resolve: {
      despesa: DespesaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'edicarApp.despesa.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
