import { ICompra } from 'app/shared/model/compra.model';
import { IVenda } from 'app/shared/model/venda.model';

export interface ICliente {
  id?: number;
  nmCliente?: string;
  cpf?: string;
  telefone?: string;
  compras?: ICompra[];
  vendas?: IVenda[];
}

export class Cliente implements ICliente {
  constructor(
    public id?: number,
    public nmCliente?: string,
    public cpf?: string,
    public telefone?: string,
    public compras?: ICompra[],
    public vendas?: IVenda[]
  ) {}
}
