import { BaseEntity } from './../../shared';

export class Aluno implements BaseEntity {
    constructor(
        public id?: number,
        public nome?: string,
        public tipoIngresso?: string,
        public bolsista?: boolean,
        public dataIngresso?: string,
        public observacoes?: string,
        public faltas?: BaseEntity[],
        public notas?: BaseEntity[],
        public entregas?: BaseEntity[],
        public turma?: BaseEntity,
    ) {
        this.bolsista = false;
    }
}
