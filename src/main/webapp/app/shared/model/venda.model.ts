import { Moment } from 'moment';
import { IVeiculo } from 'app/shared/model/veiculo.model';
import { ICliente } from 'app/shared/model/cliente.model';

export interface IVenda {
  id?: number;
  dtCompra?: Moment;
  valor?: number;
  veiculoVenda?: IVeiculo;
  veiculoTroca?: IVeiculo;
  cliente?: ICliente;
}

export class Venda implements IVenda {
  constructor(
    public id?: number,
    public dtCompra?: Moment,
    public valor?: number,
    public veiculoVenda?: IVeiculo,
    public veiculoTroca?: IVeiculo,
    public cliente?: ICliente
  ) {}
}
