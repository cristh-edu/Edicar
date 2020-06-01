import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IModelo } from 'app/shared/model/modelo.model';
import { ModeloService } from './modelo.service';

@Component({
  templateUrl: './modelo-delete-dialog.component.html',
})
export class ModeloDeleteDialogComponent {
  modelo?: IModelo;

  constructor(protected modeloService: ModeloService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.modeloService.delete(id).subscribe(() => {
      this.eventManager.broadcast('modeloListModification');
      this.activeModal.close();
    });
  }
}
