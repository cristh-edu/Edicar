import { IDespesa } from 'app/shared/model/despesa.model';
import { IModelo } from 'app/shared/model/modelo.model';

export interface IVeiculo {
  id?: number;
  placa?: string;
  cor?: string;
  ano?: number;
  valor?: number;
  despesas?: IDespesa[];
  modelo?: IModelo;
}

export class Veiculo implements IVeiculo {
  constructor(
    public id?: number,
    public placa?: string,
    public cor?: string,
    public ano?: number,
    public valor?: number,
    public despesas?: IDespesa[],
    public modelo?: IModelo
  ) {}
}
