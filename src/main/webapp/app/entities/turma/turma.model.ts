import { BaseEntity } from './../../shared';

export class Turma implements BaseEntity {
    constructor(
        public id?: number,
        public descricao?: string,
        public anoIngresso?: number,
        public semestreIngresso?: number,
        public alunos?: BaseEntity[],
        public aulas?: BaseEntity[],
    ) {
    }
}
