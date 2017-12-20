import { BaseEntity } from './../../shared';

export class Falta implements BaseEntity {
    constructor(
        public id?: number,
        public data?: string,
        public abono?: boolean,
        public motivo?: string,
        public aluno?: BaseEntity,
        public aula?: BaseEntity,
    ) {
        this.abono = false;
    }
}
