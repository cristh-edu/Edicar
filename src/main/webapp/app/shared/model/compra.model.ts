import { Moment } from 'moment';
import { IVeiculo } from 'app/shared/model/veiculo.model';
import { ICliente } from 'app/shared/model/cliente.model';

export interface ICompra {
  id?: number;
  dtCompra?: Moment;
  valor?: number;
  veiculoCompra?: IVeiculo;
  veiculoTroca?: IVeiculo;
  cliente?: ICliente;
}

export class Compra implements ICompra {
  constructor(
    public id?: number,
    public dtCompra?: Moment,
    public valor?: number,
    public veiculoCompra?: IVeiculo,
    public veiculoTroca?: IVeiculo,
    public cliente?: ICliente
  ) {}
}
