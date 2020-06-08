import { Moment } from 'moment';
import { IVeiculo } from 'app/shared/model/veiculo.model';

export interface IDespesa {
  id?: number;
  dtDespesa?: Moment;
  nmDespesa?: string;
  valor?: number;
  veiculo?: IVeiculo;
}

export class Despesa implements IDespesa {
  constructor(public id?: number, public dtDespesa?: Moment, public nmDespesa?: string, public valor?: number, public veiculo?: IVeiculo) {}
}
