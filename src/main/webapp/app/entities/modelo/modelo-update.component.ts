import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IModelo, Modelo } from 'app/shared/model/modelo.model';
import { ModeloService } from './modelo.service';
import { IMarca } from 'app/shared/model/marca.model';
import { MarcaService } from 'app/entities/marca/marca.service';

@Component({
  selector: 'jhi-modelo-update',
  templateUrl: './modelo-update.component.html',
})
export class ModeloUpdateComponent implements OnInit {
  isSaving = false;
  marcas: IMarca[] = [];

  editForm = this.fb.group({
    id: [],
    nmModelo: [null, [Validators.required, Validators.minLength(3)]],
    marca: [],
  });

  constructor(
    protected modeloService: ModeloService,
    protected marcaService: MarcaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modelo }) => {
      this.updateForm(modelo);

      this.marcaService.query().subscribe((res: HttpResponse<IMarca[]>) => (this.marcas = res.body || []));
    });
  }

  updateForm(modelo: IModelo): void {
    this.editForm.patchValue({
      id: modelo.id,
      nmModelo: modelo.nmModelo,
      marca: modelo.marca,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const modelo = this.createFromForm();
    if (modelo.id !== undefined) {
      this.subscribeToSaveResponse(this.modeloService.update(modelo));
    } else {
      this.subscribeToSaveResponse(this.modeloService.create(modelo));
    }
  }

  private createFromForm(): IModelo {
    return {
      ...new Modelo(),
      id: this.editForm.get(['id'])!.value,
      nmModelo: this.editForm.get(['nmModelo'])!.value,
      marca: this.editForm.get(['marca'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IModelo>>): void {
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

  trackById(index: number, item: IMarca): any {
    return item.id;
  }
}
