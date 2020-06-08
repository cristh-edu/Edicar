import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDespesa, Despesa } from 'app/shared/model/despesa.model';
import { DespesaService } from './despesa.service';
import { IVeiculo } from 'app/shared/model/veiculo.model';
import { VeiculoService } from 'app/entities/veiculo/veiculo.service';

@Component({
  selector: 'jhi-despesa-update',
  templateUrl: './despesa-update.component.html',
})
export class DespesaUpdateComponent implements OnInit {
  isSaving = false;
  veiculos: IVeiculo[] = [];
  dtDespesaDp: any;

  editForm = this.fb.group({
    id: [],
    dtDespesa: [null, [Validators.required]],
    nmDespesa: [null, [Validators.required, Validators.minLength(3)]],
    valor: [null, [Validators.required, Validators.min(0)]],
    veiculo: [],
  });

  constructor(
    protected despesaService: DespesaService,
    protected veiculoService: VeiculoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ despesa }) => {
      this.updateForm(despesa);

      this.veiculoService.query().subscribe((res: HttpResponse<IVeiculo[]>) => (this.veiculos = res.body || []));
    });
  }

  updateForm(despesa: IDespesa): void {
    this.editForm.patchValue({
      id: despesa.id,
      dtDespesa: despesa.dtDespesa,
      nmDespesa: despesa.nmDespesa,
      valor: despesa.valor,
      veiculo: despesa.veiculo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const despesa = this.createFromForm();
    if (despesa.id !== undefined) {
      this.subscribeToSaveResponse(this.despesaService.update(despesa));
    } else {
      this.subscribeToSaveResponse(this.despesaService.create(despesa));
    }
  }

  private createFromForm(): IDespesa {
    return {
      ...new Despesa(),
      id: this.editForm.get(['id'])!.value,
      dtDespesa: this.editForm.get(['dtDespesa'])!.value,
      nmDespesa: this.editForm.get(['nmDespesa'])!.value,
      valor: this.editForm.get(['valor'])!.value,
      veiculo: this.editForm.get(['veiculo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDespesa>>): void {
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

  trackById(index: number, item: IVeiculo): any {
    return item.id;
  }
}
