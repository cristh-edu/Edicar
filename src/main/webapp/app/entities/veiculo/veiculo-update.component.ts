import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IVeiculo, Veiculo } from 'app/shared/model/veiculo.model';
import { VeiculoService } from './veiculo.service';
import { IModelo } from 'app/shared/model/modelo.model';
import { ModeloService } from 'app/entities/modelo/modelo.service';

@Component({
  selector: 'jhi-veiculo-update',
  templateUrl: './veiculo-update.component.html',
})
export class VeiculoUpdateComponent implements OnInit {
  isSaving = false;
  modelos: IModelo[] = [];

  editForm = this.fb.group({
    id: [],
    placa: [null, [Validators.required]],
    cor: [null, [Validators.required]],
    ano: [null, [Validators.required, Validators.min(1900), Validators.max(2100)]],
    valor: [null, [Validators.required, Validators.min(0)]],
    modelo: [],
  });

  constructor(
    protected veiculoService: VeiculoService,
    protected modeloService: ModeloService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ veiculo }) => {
      this.updateForm(veiculo);

      this.modeloService.query().subscribe((res: HttpResponse<IModelo[]>) => (this.modelos = res.body || []));
    });
  }

  updateForm(veiculo: IVeiculo): void {
    this.editForm.patchValue({
      id: veiculo.id,
      placa: veiculo.placa,
      cor: veiculo.cor,
      ano: veiculo.ano,
      valor: veiculo.valor,
      modelo: veiculo.modelo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const veiculo = this.createFromForm();
    if (veiculo.id !== undefined) {
      this.subscribeToSaveResponse(this.veiculoService.update(veiculo));
    } else {
      this.subscribeToSaveResponse(this.veiculoService.create(veiculo));
    }
  }

  private createFromForm(): IVeiculo {
    return {
      ...new Veiculo(),
      id: this.editForm.get(['id'])!.value,
      placa: this.editForm.get(['placa'])!.value,
      cor: this.editForm.get(['cor'])!.value,
      ano: this.editForm.get(['ano'])!.value,
      valor: this.editForm.get(['valor'])!.value,
      modelo: this.editForm.get(['modelo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVeiculo>>): void {
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

  trackById(index: number, item: IModelo): any {
    return item.id;
  }
}
