import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IModelo, Modelo } from 'app/shared/model/modelo.model';
import { ModeloService } from './modelo.service';
import { ModeloComponent } from './modelo.component';
import { ModeloDetailComponent } from './modelo-detail.component';
import { ModeloUpdateComponent } from './modelo-update.component';

@Injectable({ providedIn: 'root' })
export class ModeloResolve implements Resolve<IModelo> {
  constructor(private service: ModeloService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IModelo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((modelo: HttpResponse<Modelo>) => {
          if (modelo.body) {
            return of(modelo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Modelo());
  }
}

export const modeloRoute: Routes = [
  {
    path: '',
    component: ModeloComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'edicarApp.modelo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ModeloDetailComponent,
    resolve: {
      modelo: ModeloResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'edicarApp.modelo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ModeloUpdateComponent,
    resolve: {
      modelo: ModeloResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'edicarApp.modelo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ModeloUpdateComponent,
    resolve: {
      modelo: ModeloResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'edicarApp.modelo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
