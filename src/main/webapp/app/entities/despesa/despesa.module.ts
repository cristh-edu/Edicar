import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EdicarSharedModule } from 'app/shared/shared.module';
import { DespesaComponent } from './despesa.component';
import { DespesaDetailComponent } from './despesa-detail.component';
import { DespesaUpdateComponent } from './despesa-update.component';
import { DespesaDeleteDialogComponent } from './despesa-delete-dialog.component';
import { despesaRoute } from './despesa.route';

@NgModule({
  imports: [EdicarSharedModule, RouterModule.forChild(despesaRoute)],
  declarations: [DespesaComponent, DespesaDetailComponent, DespesaUpdateComponent, DespesaDeleteDialogComponent],
  entryComponents: [DespesaDeleteDialogComponent],
})
export class EdicarDespesaModule {}
