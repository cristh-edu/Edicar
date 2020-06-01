import { IModelo } from 'app/shared/model/modelo.model';

export interface IMarca {
  id?: number;
  nmMarca?: string;
  modelos?: IModelo[];
}

export class Marca implements IMarca {
  constructor(public id?: number, public nmMarca?: string, public modelos?: IModelo[]) {}
}
