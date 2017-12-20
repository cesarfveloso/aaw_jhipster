import { BaseEntity } from './../../shared';

export class Entrega implements BaseEntity {
    constructor(
        public id?: number,
        public dataEntrega?: string,
        public entregaNoPrazo?: boolean,
        public aluno?: BaseEntity,
        public atividade?: BaseEntity,
    ) {
        this.entregaNoPrazo = false;
    }
}
