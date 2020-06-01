import { IVeiculo } from 'app/shared/model/veiculo.model';
import { IMarca } from 'app/shared/model/marca.model';

export interface IModelo {
  id?: number;
  nmModelo?: string;
  veiculos?: IVeiculo[];
  marca?: IMarca;
}

export class Modelo implements IModelo {
  constructor(public id?: number, public nmModelo?: string, public veiculos?: IVeiculo[], public marca?: IMarca) {}
}
