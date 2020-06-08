import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDespesa } from 'app/shared/model/despesa.model';
import { DespesaService } from './despesa.service';

@Component({
  templateUrl: './despesa-delete-dialog.component.html',
})
export class DespesaDeleteDialogComponent {
  despesa?: IDespesa;

  constructor(protected despesaService: DespesaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.despesaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('despesaListModification');
      this.activeModal.close();
    });
  }
}
