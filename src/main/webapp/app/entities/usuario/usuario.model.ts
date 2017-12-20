import { BaseEntity } from './../../shared';

export class Usuario implements BaseEntity {
    constructor(
        public id?: number,
        public cpf?: number,
        public ativo?: boolean,
        public permissao?: BaseEntity,
    ) {
        this.ativo = false;
    }
}
