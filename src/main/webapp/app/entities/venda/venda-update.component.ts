import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IVenda, Venda } from 'app/shared/model/venda.model';
import { VendaService } from './venda.service';
import { IVeiculo } from 'app/shared/model/veiculo.model';
import { VeiculoService } from 'app/entities/veiculo/veiculo.service';
import { ICliente } from 'app/shared/model/cliente.model';
import { ClienteService } from 'app/entities/cliente/cliente.service';

type SelectableEntity = IVeiculo | ICliente;

@Component({
  selector: 'jhi-venda-update',
  templateUrl: './venda-update.component.html',
})
export class VendaUpdateComponent implements OnInit {
  isSaving = false;
  veiculovendas: IVeiculo[] = [];
  veiculotrocas: IVeiculo[] = [];
  clientes: ICliente[] = [];
  dtCompraDp: any;

  editForm = this.fb.group({
    id: [],
    dtCompra: [null, [Validators.required]],
    valor: [null, [Validators.required]],
    veiculoVenda: [],
    veiculoTroca: [],
    cliente: [],
  });

  constructor(
    protected vendaService: VendaService,
    protected veiculoService: VeiculoService,
    protected clienteService: ClienteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ venda }) => {
      this.updateForm(venda);

      this.veiculoService
        .query({ filter: 'venda-is-null' })
        .pipe(
          map((res: HttpResponse<IVeiculo[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IVeiculo[]) => {
          if (!venda.veiculoVenda || !venda.veiculoVenda.id) {
            this.veiculovendas = resBody;
          } else {
            this.veiculoService
              .find(venda.veiculoVenda.id)
              .pipe(
                map((subRes: HttpResponse<IVeiculo>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IVeiculo[]) => (this.veiculovendas = concatRes));
          }
        });

      this.veiculoService
        .query({ filter: 'venda-is-null' })
        .pipe(
          map((res: HttpResponse<IVeiculo[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IVeiculo[]) => {
          if (!venda.veiculoTroca || !venda.veiculoTroca.id) {
            this.veiculotrocas = resBody;
          } else {
            this.veiculoService
              .find(venda.veiculoTroca.id)
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

  updateForm(venda: IVenda): void {
    this.editForm.patchValue({
      id: venda.id,
      dtCompra: venda.dtCompra,
      valor: venda.valor,
      veiculoVenda: venda.veiculoVenda,
      veiculoTroca: venda.veiculoTroca,
      cliente: venda.cliente,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const venda = this.createFromForm();
    if (venda.id !== undefined) {
      this.subscribeToSaveResponse(this.vendaService.update(venda));
    } else {
      this.subscribeToSaveResponse(this.vendaService.create(venda));
    }
  }

  private createFromForm(): IVenda {
    return {
      ...new Venda(),
      id: this.editForm.get(['id'])!.value,
      dtCompra: this.editForm.get(['dtCompra'])!.value,
      valor: this.editForm.get(['valor'])!.value,
      veiculoVenda: this.editForm.get(['veiculoVenda'])!.value,
      veiculoTroca: this.editForm.get(['veiculoTroca'])!.value,
      cliente: this.editForm.get(['cliente'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVenda>>): void {
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
