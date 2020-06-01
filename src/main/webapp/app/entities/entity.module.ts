import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'marca',
        loadChildren: () => import('./marca/marca.module').then(m => m.EdicarMarcaModule),
      },
      {
        path: 'modelo',
        loadChildren: () => import('./modelo/modelo.module').then(m => m.EdicarModeloModule),
      },
      {
        path: 'veiculo',
        loadChildren: () => import('./veiculo/veiculo.module').then(m => m.EdicarVeiculoModule),
      },
      {
        path: 'compra',
        loadChildren: () => import('./compra/compra.module').then(m => m.EdicarCompraModule),
      },
      {
        path: 'venda',
        loadChildren: () => import('./venda/venda.module').then(m => m.EdicarVendaModule),
      },
      {
        path: 'cliente',
        loadChildren: () => import('./cliente/cliente.module').then(m => m.EdicarClienteModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EdicarEntityModule {}
