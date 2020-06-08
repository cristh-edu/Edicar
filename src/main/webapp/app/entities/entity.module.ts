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
      {
        path: 'despesa',
        loadChildren: () => import('./despesa/despesa.module').then(m => m.EdicarDespesaModule),
      }
    ]),
  ],
})
export class EdicarEntityModule {}
