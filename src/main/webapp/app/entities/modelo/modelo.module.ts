import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EdicarSharedModule } from 'app/shared/shared.module';
import { ModeloComponent } from './modelo.component';
import { ModeloDetailComponent } from './modelo-detail.component';
import { ModeloUpdateComponent } from './modelo-update.component';
import { ModeloDeleteDialogComponent } from './modelo-delete-dialog.component';
import { modeloRoute } from './modelo.route';

@NgModule({
  imports: [EdicarSharedModule, RouterModule.forChild(modeloRoute)],
  declarations: [ModeloComponent, ModeloDetailComponent, ModeloUpdateComponent, ModeloDeleteDialogComponent],
  entryComponents: [ModeloDeleteDialogComponent],
})
export class EdicarModeloModule {}
