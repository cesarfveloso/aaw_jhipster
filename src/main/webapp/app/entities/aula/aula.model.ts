import { BaseEntity } from './../../shared';

export class Aula implements BaseEntity {
    constructor(
        public id?: number,
        public horarioInicial?: string,
        public horarioFinal?: string,
        public faltas?: BaseEntity[],
        public turma?: BaseEntity,
    ) {
    }
}
