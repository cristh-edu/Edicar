import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ICompra, Compra } from 'app/shared/model/compra.model';
import { CompraService } from './compra.service';
import { IVeiculo } from 'app/shared/model/veiculo.model';
import { VeiculoService } from 'app/entities/veiculo/veiculo.service';
import { ICliente } from 'app/shared/model/cliente.model';
import { ClienteService } from 'app/entities/cliente/cliente.service';

type SelectableEntity = IVeiculo | ICliente;

@Component({
  selector: 'jhi-compra-update',
  templateUrl: './compra-update.component.html',
})
export class CompraUpdateComponent implements OnInit {
  isSaving = false;
  veiculocompras: IVeiculo[] = [];
  veiculotrocas: IVeiculo[] = [];
  clientes: ICliente[] = [];
  dtCompraDp: any;

  editForm = this.fb.group({
    id: [],
    dtCompra: [null, [Validators.required]],
    valor: [null, [Validators.required]],
    veiculoCompra: [],
    veiculoTroca: [],
    cliente: [],
  });

  constructor(
    protected compraService: CompraService,
    protected veiculoService: VeiculoService,
    protected clienteService: ClienteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ compra }) => {
      this.updateForm(compra);

      this.veiculoService
        .query({ filter: 'compra-is-null' })
        .pipe(
          map((res: HttpResponse<IVeiculo[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IVeiculo[]) => {
          if (!compra.veiculoCompra || !compra.veiculoCompra.id) {
            this.veiculocompras = resBody;
          } else {
            this.veiculoService
              .find(compra.veiculoCompra.id)
              .pipe(
                map((subRes: HttpResponse<IVeiculo>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IVeiculo[]) => (this.veiculocompras = concatRes));
          }
        });

      this.veiculoService
        .query({ filter: 'compra-is-null' })
        .pipe(
          map((res: HttpResponse<IVeiculo[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IVeiculo[]) => {
          if (!compra.veiculoTroca || !compra.veiculoTroca.id) {
            this.veiculotrocas = resBody;
          } else {
            this.veiculoService
              .find(compra.veiculoTroca.id)
              .pipe(
                map((subRes: HttpResponse<IVeiculo>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IVeiculo[]) => (this.veiculotrocas = concatRes));
          }
        });

      this.clienteService.query().subscribe((res: HttpResponse<ICliente[]>) => (this.clientes = res.body || []));
    });
  }

  updateForm(compra: ICompra): void {
    this.editForm.patchValue({
      id: compra.id,
      dtCompra: compra.dtCompra,
      valor: compra.valor,
      veiculoCompra: compra.veiculoCompra,
      veiculoTroca: compra.veiculoTroca,
      cliente: compra.cliente,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const compra = this.createFromForm();
    if (compra.id !== undefined) {
      this.subscribeToSaveResponse(this.compraService.update(compra));
    } else {
      this.subscribeToSaveResponse(this.compraService.create(compra));
    }
  }

  private createFromForm(): ICompra {
    return {
      ...new Compra(),
      id: this.editForm.get(['id'])!.value,
      dtCompra: this.editForm.get(['dtCompra'])!.value,
      valor: this.editForm.get(['valor'])!.value,
      veiculoCompra: this.editForm.get(['veiculoCompra'])!.value,
      veiculoTroca: this.editForm.get(['veiculoTroca'])!.value,
      cliente: this.editForm.get(['cliente'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompra>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
